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
            updateArticle: function(id, data) {
                return $http({
                    method: 'PUT',
                    url: '/articles/' + id,
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
            },
            prepareTags: function (input) {
                return input.trim().split(" ").map(function(tag) {
                    tag = tag.trim();
                    return tag.charAt(0).toUpperCase() + tag.slice(1).toLowerCase();
                });
            }
        }

    }]);