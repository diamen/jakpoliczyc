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
            getArticlesByTagId: function (ids) {
                var config = {
                    method: 'GET',
                    url: '/articles/tag',
                    headers: {'Content-Type': 'application/json'},
                    params: {
                        ids: ids
                    }
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
            getArticlesByMenuId: function (id) {
                var config = {
                    method: 'GET',
                    url: '/articles/menu/' + id,
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
                return angular.isUndefined(input) ? input : input.trim().split(" ").map(function(tag) {
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