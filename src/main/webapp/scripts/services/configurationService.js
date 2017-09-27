angular.module('jakPoliczycServices')
    .service("configurationService", ['$http', function($http) {

        return {
            getConfigs: function () {
                return $http({
                    cache: 'true',
                    method: 'GET',
                    url: '/configuration',
                    headers: {'Content-Type': 'application/json'}
                });
            },
            postConfig: function (config) {
                return $http({
                    method: 'POST',
                    url: '/configuration',
                    headers: {'Content-Type': 'application/json'},
                    data: config
                })
            }
        }

    }]);