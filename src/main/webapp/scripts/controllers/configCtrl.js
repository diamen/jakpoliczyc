angular.module('jakPoliczycControllers')
    .controller('configCtrl', function ($scope, configurationService, modalService) {

        configurationService.getConfigs().then(function success(response) {
            $scope.configs = response.data;
            $scope.configs.map(function (e) {
                e.editable = false;
                return e;
            });
        }, function error() {
            $scope.configs = [];
        });

        $scope.add = function () {
            $scope.configs.push({'keyy': '', 'value': '', 'editable': true});
        };

        $scope.edit = function (index) {
            $scope.configs[index].editable = !$scope.configs[index].editable;
        };

        $scope.delete = function (index) {
            $scope.configs.splice(index, 1);
        };

        $scope.$watch('configs', function () {
            if (angular.isDefined($scope.configs)) {
                $scope.disabled = !isValid($scope.configs);
            }
        }, true);

        var isValid = function (config) {
            var unique = config.map(function (e) {
                    return e.keyy;
                }).filter(function (value, index, self) {
                    return self.indexOf(value) === index;
                }).length === config.length;

            if (!unique) {
                return unique;
            }

            var isEmpty = config.filter(function (e) {
                    return e.value === '' || angular.isUndefined(e.value)
                        || e.keyy === '' || angular.isUndefined(e.keyy);
                }).length > 0;

            return !isEmpty;
        };

        var postConfig = function (configs) {
            configurationService.postConfig(configs).then(function success() {
                $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertConSuc})
            }, function error() {
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertConErr})
            });
        };

        $scope.openModal = function () {
            modalService.execute(postConfig, $scope.language.msgConfSub, $scope.configs.map(function(e) {
                return {'keyy': e.keyy, 'value': e.value};
            }));
        };

    });