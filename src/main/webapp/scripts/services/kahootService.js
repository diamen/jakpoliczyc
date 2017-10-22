angular.module('jakPoliczycServices')
    .service("kahootService", ['$http', 'constants', function ($http, constants) {

        var getKahoots = function () {
            return $http({
                method: 'GET',
                url: '/kahoot',
                headers: {'Content-Type': 'application/json'}
            });
        };

        var postConfig = function (config) {
            return $http({
                method: 'POST',
                url: '/kahoot',
                headers: {'Content-Type': 'application/json'},
                data: config
            })
        };

        var deleteConfig = function (id) {
            return $http({
                method: 'DELETE',
                url: '/kahoot/' + id,
                headers: {'Content-Type': 'application/json'},
            })
        };

        var addTranslationOfDifficulties = function (arr) {
            var copy = [];
            angular.copy(arr, copy);
            return copy.map(function (e) {
                var kahDif = constants.KahootDifficulties;
                for (var kahootDiff in kahDif) {
                    if (kahDif[kahootDiff].name === e.kahootDifficulties) {
                        e.languageKey = kahDif[kahootDiff].languageKey;
                        return e;
                    }
                }
            });
        };

        return {
            addTranslationOfDifficulties: addTranslationOfDifficulties,
            getKahoots: getKahoots,
            postConfig: postConfig,
            deleteConfig: deleteConfig
        }

    }]);
