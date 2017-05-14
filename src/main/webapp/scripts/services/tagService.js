angular.module('jakPoliczycServices')
    .service("tagService", ['$http', function($http) {

        return {
            getTags: function () {
                return $http({
                    cache: true,
                    headers: {'Content-Type': 'application/json'},
                    method: 'GET',
                    url: '/tags'
                });
            }
        }
    }]);