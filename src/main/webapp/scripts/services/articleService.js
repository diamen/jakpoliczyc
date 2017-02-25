angular.module('jakPoliczycServices')
    .service("articleService", ['$http', function($http) {

        return {
            postArticle: function(menu, article, tags) {
                return $http({
                    method: 'POST',
                    url: '/article',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    transformRequest: function(obj) {
                        var str = [];
                        for(var p in obj)
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        return str.join("&");
                    },
                    data: {
                        menu: {
                            name: menu.name,
                            parentId: menu.parentId
                        },
                        article: {
                            title: article.title,
                            intro: article.intro,
                            content: article.content
                        },
                        tags: tags
                    }
                });
            }
        }

    }]);