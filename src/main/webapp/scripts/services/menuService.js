angular.module('jakPoliczycServices')
    .service("menuService", ['$http', function ($http) {

        return {
            getMenus: function () {
                return $http({
                    method: 'GET',
                    url: '/menu'
                });
            },
            getMenuWithArticle: function () {
                return $http({
                    method: 'GET',
                    url: '/menuWithArticle'
                });
            },
            postMenu: function (data) {
                return $http({
                    method: 'POST',
                    url: '/menu',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            }
        }

    }]);