angular.module('jakPoliczycControllers')
    .controller('articlesCtrl', function($scope, $http, jpartfilter) {

        $scope.articles = [];
        $scope.filteredArticles = [];
        $scope.orderProp = 'title';

        $scope.header = { values: {} };
        $scope.header.names = { TITLE: "title", KIND: "kind", DATE: "date" };

        angular.forEach($scope.header.names, function (value) {
            $scope.header.values[value] = { selected: false, reversed: false };
        });

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

        ($scope.orderBy = function (value) {
            angular.forEach($scope.header.values, function (elem, key) {

                if (key === value) {

                    if (elem.selected) {
                        elem.selected = false;
                        elem.reversed = true;
                        $scope.orderProp = reverse(value);
                        return;
                    }

                    $scope.orderProp = value;

                    if (elem.reversed) {
                        elem.selected = true;
                        elem.reversed = false;
                        return;
                    }

                    elem.selected = true;
                    return;
                }

                elem.selected = false;
                elem.reversed = false;
            });
        })($scope.header.names.DATE);

        $scope.getKind = function (letter) {
            if (letter === 'Z')
                return "ZADANIE";

            if (letter === 'T')
                return "TEORIA";

            throw new Error('Podano nieprawidłowy rodzaj artykuły. Dopuszczalne rodzaje to "Z" oraz "T"');
        };

        function reverse(value) {
            return '-' + value;
        }
    });