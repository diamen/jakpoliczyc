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
            },
            getSingle: function (id) {
                return $http({
                    method: 'GET',
                    url: '/articles/' + id
                })
            },
            postComment: function (id, author, content) {
                return $http({
                    method: 'POST',
                    url: '/articles/' + id + '/comment',
                    headers: {'Content-Type': 'application/json'},
                    data: {
                        author: author,
                        content: content
                    }
                })
            },
            deleteComment: function (articleId, commentId) {
                return $http({
                    method: 'DELETE',
                    url: '/articles/' + articleId + '/comment/' + commentId
                })
            }
        }

    }]);