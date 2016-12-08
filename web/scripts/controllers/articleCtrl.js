angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function($scope, $http, jpartfilter) {

        $scope.articles = [];
        $scope.filteredArticles = [];

        $http({
            method: 'GET',
            url: '/articles'
        }).then(function success(response) {
            $scope.articles = response.data;
        }, function error() {
            throw new Error("HTTP error");
        });

        $scope.$on('tags-down', function (event, args) {
            $scope.filteredArticles = jpartfilter($scope.articles, 'tags', args);
        });

        $scope.$on('menu-down', function (event, args) {

            $http({
                method: 'GET',
                url: '/articles/menuid/' + args
            }).then(function success(response) {
                $scope.articles = response.data;
                angular.copy($scope.articles, $scope.filteredArticles);
            }, function error() {
                throw new Error("HTTP error");
            });
        });

        $scope.addComment = function (author, content) {
          console.log(author + ' dodał komentarz o treści: ' + content);
        };

    });