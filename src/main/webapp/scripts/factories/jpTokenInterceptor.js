angular.module('jakPoliczycFactories')
    .factory('jpTokenInterceptor', ['$cookies', function($cookies) {

        return {
            request: function (config) {
                if ($cookies.get("TOKEN")) {
                    config.headers['Authorization'] = 'Bearer ' + $cookies.get("TOKEN");
                }

                return config;
            }
        }

    }]);
