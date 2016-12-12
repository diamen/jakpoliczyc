angular.module('jakPoliczycRouters', [])
    .config(function($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/articles");

        $stateProvider

            /* ARTICLES SECTION */
            .state("articles", {
                url: "/articles",
                views: {
                    '': {
                        templateUrl: "/views/partials/partial.articles.html"
                    },
                    "articlesView@articles": {
                        controller: "articlesCtrl",
                        templateUrl: "/views/articles.html"
                    }
                }
            })

            .state("articles.id", {
                url: "/{id:[0-9]{1,9}}",
                views: {
                    "articlesView@articles": {
                        controller: "articleCtrl",
                        templateUrl: "/views/article.html"
                    }
                }
            });

    });