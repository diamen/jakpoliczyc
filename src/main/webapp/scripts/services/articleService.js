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
            getArticles: function() {
                var config = {
                    cache: 'true',
                    method: 'GET',
                    url: '/articles',
                    headers: {'Content-Type': 'application/json'}
                };

                var fire = function () {
                    return $http(config);
                };

                var getConfig = function () {
                  return config;
                };

                var setConfig = function (conf) {
                    config = conf;
                };

                return {
                    fire: fire,
                    getConfig: getConfig,
                    setConfig: setConfig
                };
            },
            getArticlesByMenuId: function (id) {
                return $http({
                    method: 'GET',
                    url: '/articles/menu/' + id,
                    headers: {'Content-Type': 'application/json'}
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
            deleteArticle: function (id) {
                return $http({
                    method: 'DELETE',
                    url: '/articles/' + id
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
            },
            prepareYoutubeUrl: function (url) {
                var toWatch = url.substr(url.indexOf('embed/')).substring(6);
                var youtube = url.substr(0, url.indexOf('embed'));
                return  (youtube + "watch?v=" + toWatch);
            }
        }

    }]);