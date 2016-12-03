angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function($scope, $http) {

        $scope.articles = [];
        $scope.filteredArticles = [];

        $http({
            method: 'GET',
            url: '/articles'
        }).then(function success(response) {
            $scope.articles = response.data;
            $scope.filter($scope.articles, []);
        }, function error() {
            throw new Error("HTTP error");
        });

        $scope.$on('tags-down', function (event, args) {
            $scope.filteredArticles = $scope.filter($scope.articles, args);
        });

        $scope.filter = function (articles, tags) {
            filteredArticles = [];

            if (tags.length === 0 || angular.isUndefined(tags))
                return articles;

            articles.forEach(function (article) {
                _.intersection(article.tags, tags).length > 0 ? filteredArticles.push(article) : '';
            });

            return filteredArticles;
        };

        $scope.addComment = function (author, content) {
          console.log(author + ' dodał komentarz o treści: ' + content);
        };

    });