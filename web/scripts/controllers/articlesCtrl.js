angular.module('jakPoliczycControllers')
    .controller('articlesCtrl', function($scope, $http, jpartfilter) {

        $scope.articles = [];
        $scope.filteredArticles = [];

        $http({
            method: 'GET',
            url: '/articles'
        }).then(function success(response) {
            $scope.articles = response.data;
            angular.copy($scope.articles, $scope.filteredArticles);
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

        $scope.getKind = function (letter) {
            if (letter === 'E')
                return "ZADANIE";

            if (letter === 'T')
                return "TEORIA";

            throw new Error('Podano nieprawidłowy rodzaj artykuły. Dopuszczalne rodzaje to "Z" oraz "T"');
        };

    });