angular.module('jakPoliczycServices')
    .service("menuService", ['$http', function($http) {

        return {
            getMenus: function() {
                return $http({
                    cache: true,
                    method: 'GET',
                    url: '/menu'
                });
            }
        }

    }]);