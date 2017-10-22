angular.module('jakPoliczycControllers')
    .controller('kahootConfigCtrl', function ($scope, $window, constants, kahootService, modalService, jpNotifier) {

        var urlRegex = new RegExp('^((?!http:\/\/)(?!https:\/\/)(?!www\.).)*$', "im");

        var temp = new Map();

        $scope.langKeys = (function () {
            var arr = [];
            for (var kahootDiff in constants.KahootDifficulties) {
                arr.push(constants.KahootDifficulties[kahootDiff].languageKey);
            }
            return arr;
        })();

        kahootService.getKahoots().then(function success(response) {

            if (response.data) {
                $scope.kahoots = kahootService.addTranslationOfDifficulties(response.data).map(function (e) {
                    e.editable = false;
                    return e;
                });
            }
        });

        $scope.$watch('kahoots', function () {
            if (angular.isDefined($scope.kahoots)) {
                $scope.disabled = !isValid($scope.kahoots);
            }
        }, true);

        var isValid = function (config) {
            var unique = config.map(function (e) {
                    return e.title;
                }).filter(function (value, index, self) {
                    return self.indexOf(value) === index;
                }).length === config.length;

            if (!unique) {
                return unique;
            }

            var isEmpty = config.filter(function (e) {
                    return e.title === '' || angular.isUndefined(e.title)
                        || e.url === '' || angular.isUndefined(e.url)
                        || e.languageKey === '' || angular.isUndefined(e.languageKey);
                }).length > 0;

            if (isEmpty) {
                return !isEmpty;
            }

            var isUrl = config.map(function (e) {
                    return e.url;
                }).filter(function (value) {
                    if (!urlRegex.test(value)) {
                        return value;
                    }
                }).length === config.length;

            return isUrl;
        };

        $scope.delete = function (index) {
            var kahoot = $scope.kahoots[index];
            kahoot.id ? $scope.deleteConfig(index, kahoot.id) : $scope.deleteRow(index);
        };

        $scope.editRow = function (index) {
            var kahoot = $scope.kahoots[index];
            if (kahoot.id)
            {
                if (!kahoot.editable) {
                    var clonedKahoot = {};
                    angular.copy(kahoot, clonedKahoot);
                    temp.set(kahoot.id, clonedKahoot);
                } else {
                    $scope.kahoots[index] = temp.get(kahoot.id);
                }
            }
            kahoot.editable = !kahoot.editable;
        };

        $scope.deleteRow = function (index) {
            $scope.kahoots.splice(index, 1);
        };

        $scope.addRow = function () {
            $scope.kahoots.push({'title': '', 'languageKey': '', 'url': '', 'editable': true});
        };

        $scope.postConfig = function (index, config) {
            modalService.execute(postConfig, $scope.language.msgConfSub, {'index': index, 'config': adjustRequestData(config)});
        };

        var postConfig = function (data) {
            var config = data.config;
            kahootService.postConfig(config).then(function success() {
                if (!config.id) {
                    $window.location.reload();
                }
                $scope.kahoots[data.index].editable = false;
                jpNotifier.notifySuccess("alertConSuc");
            }, function failure() {
                jpNotifier.notifyError("alertConErr");
            });
        };

        $scope.deleteConfig = function (index, id) {
            modalService.execute(deleteConfig, $scope.language.msgConfDel, {'index': index, 'id': id});
        };

        var deleteConfig = function (data) {
            kahootService.deleteConfig(data.id).then(function success() {
               $scope.deleteRow(data.index);
               jpNotifier.notifySuccess("alertConDelSuc");
            }, function failure() {
                jpNotifier.notifyError("alertConDelErr");
            });
        };

        var adjustRequestData = function (config) {
            var langValue = (function () {
                for (var kahootDiff in constants.KahootDifficulties) {
                    if (constants.KahootDifficulties[kahootDiff].languageKey === config.languageKey) {
                        return constants.KahootDifficulties[kahootDiff].name;
                    }
                }
            })();

            return {'id': config.id, 'title': config.title, 'url': config.url, 'kahootDifficulties': langValue};
        }

    });