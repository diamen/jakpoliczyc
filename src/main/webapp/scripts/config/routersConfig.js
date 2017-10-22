angular.module('jakPoliczycRouters', [])
    .config(function($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/articles");

        $stateProvider

            /* ARTICLES SECTION */
            .state("articles", {
                url: "/articles",
                views: {
                    '': {
                        templateUrl: "views/partials/partial.articles.html"
                    },
                    "articlesView@articles": {
                        controller: "articlesCtrl",
                        templateUrl: "views/articles.html"
                    }
                }
            })

            .state("articles.id", {
                url: "/{id:[0-9]{1,9}}",
                views: {
                    "articlesView@articles": {
                        controller: "articleCtrl",
                        templateUrl: "views/article.html"
                    }
                }
            })

            .state("articles.add", {
                url: "/add",
                views: {
                    "articlesView@articles": {
                        controller: "addCtrl",
                        templateUrl: "views/add-article.html"
                    }
                },
                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            .state("articles.storage", {
                url: "/storage",
                views: {
                    "articlesView@articles": {
                        controller: "storageCtrl",
                        templateUrl: "views/storage.html"
                    }
                },
                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            .state("articles.storage.id", {
                url: "/{id:[0-9]{1,9}}",
                views: {
                    "articlesView@articles": {
                        controller: "singleStorageCtrl",
                        templateUrl: "views/single-storage.html"
                    }
                },
                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            .state('kahoot', {
                url: "/kahoot",
                controller: "kahootCtrl",
                templateUrl: "views/kahoot.html"
            })

            /* BROADCAST SECTION */
            .state("broadcast", {
                url: "/broadcast",
                controller: "broadcasterCtrl",
                templateUrl: "views/broadcaster.html",

                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            /* LOGIN SECTION */
            .state("login", {
                url: "/login",
                controller: "loginCtrl",
                templateUrl: "views/login.html"
            })

            /* ADMIN SECTION */
            .state("config", {
                url: "/config",
                controller: "configCtrl",
                templateUrl: "views/config.html",

                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            .state("kahoot-config", {
                url: "/kahootconfig",
                controller: "kahootConfigCtrl",
                templateUrl: "views/kahoot-config.html",

                data: {
                    roles: ['ROLE_ADMIN']
                }
            })

            /* ERROR SECTION */
            .state("error", {
                url: "/error/{no:[0-9]{1,9}}",
                templateUrl: "views/error.html",
                controller: function ($scope, $stateParams) {
                    $scope.no = $stateParams.no;
                }
            });

    });