angular.module('jakPoliczycServices')
    .service("storageService", ['$http', function($http) {

        return {
            postStorage: function(data) {
                return $http({
                    method: 'POST',
                    url: '/storage',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            },
            updateStorage: function(id, data) {
                return $http({
                    method: 'PUT',
                    url: '/storage/' + id,
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            },
            deleteStorage: function(id) {
                return $http({
                    method: 'DELETE',
                    url: '/storage/' + id
                });
            },
            getStorage: function(id) {
                return $http({
                    method: 'GET',
                    url: '/storage/' + id,
                    headers: {'Content-Type': 'application/json'}
                });
            },
            getStorages: function() {
                var config = {
                    cache: 'true',
                    method: 'GET',
                    url: '/storage',
                    headers: {'Content-Type': 'application/json'}
                };

                var fire = function (config) {
                    return $http(config);
                };

                var getConfig = function () {
                    var configCopy = {};
                    angular.copy(config, configCopy);
                    return configCopy;
                };

                return {
                    fire: fire,
                    getConfig: getConfig
                };
            },
            publishStorage: function (id, data) {
                return $http({
                    method: 'POST',
                    url: '/storage/publish/' + id,
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            }
        }

    }]);