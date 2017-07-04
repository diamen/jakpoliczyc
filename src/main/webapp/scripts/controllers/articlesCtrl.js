angular.module('jakPoliczycControllers')
    .controller('articlesCtrl', function($scope, $rootScope, $http, jpArtfilter, articleService) {

        $scope.articles = [];
        $scope.filteredArticles = [];
        $scope.isFilter = false;

        $scope.header = { values: {} };
        $scope.header.names = { TITLE: "story.title", DATE: "addedDate" };
        $scope.orderProp = $scope.header.names.DATE;

        var _articlesLength;

        angular.forEach($scope.header.names, function (value) {
            $scope.header.values[value] = { selected: false, reversed: false };
        });

        $scope.$on('tags-down', function (event, args) {
            if (angular.isUndefined(args))
                return;

            filterTags(args);
        });

        $scope.$on('menu-down', function (event, args) {
            if (angular.isUndefined(args)) {
                if ($scope.articles.length !== $scope.filteredArticles.length)
                    angular.copy($scope.articles, $scope.filteredArticles);
                $scope.isFilter = false;
                return;
            }

            filterTags();
            args = args || {};

            $scope.filteredArticles = jpArtfilter($scope.articles, 'menu', [args].map(function(menu) { return menu.id; })) || [];
            $scope.isFilter = true;
        });


        function filterTags(arg) {
            arg = arg || [];
            $scope.filteredArticles = jpArtfilter($scope.articles, 'tags', arg.map(function(tag) { return tag.id; })) || [];

            if (angular.isUndefined(_articlesLength))
                return;

            $scope.chosenTags = arg.map(function(tag) { return tag.name; }).join(", ");
            $scope.isFilter = ($scope.filteredArticles.length !== _articlesLength) || $scope.chosenTags.length > 0;
        }

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

        function reverse(value) {
            return '-' + value;
        }

        $scope.getData = function () {
            return articleService.getArticles();
        };

        $scope.httpSuccess = function (data) {
            $scope.articles = data;
            _articlesLength = $scope.articles.length;
            angular.copy($scope.articles, $scope.filteredArticles);
        };

        $scope.httpFailure = function (response) {
            console.log(response);
        };

    });