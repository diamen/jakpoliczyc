angular.module('jakPoliczycServices')
    .service("articleService", ['$http', function($http) {

        return {
            postArticle: function(data) {
                return $http({
                    method: 'POST',
                    url: '/articles',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            }
        }

    }]);