angular.module('jakPoliczycServices')
    .service("broadcasterService", ['$http', function($http) {

        return {
            broadcast: function(data) {
                return $http({
                    method: 'POST',
                    url: '/broadcaster',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            },
            contact: function (data) {
                return $http({
                    method: 'POST',
                    url: '/contact',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                })
            }
        }

    }]);