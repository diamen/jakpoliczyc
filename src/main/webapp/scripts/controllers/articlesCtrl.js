angular.module('jakPoliczycControllers')
    .controller('articlesCtrl', function($scope, $rootScope, articleService) {

        $scope.articles = [];

        $scope.$on('filter-down', function (event, args) {
            if (angular.isUndefined(args)) {
                $scope.control = articleService.getArticles();
                $scope.isFilter = false;
                return;
            }
            $scope.control = args;
            $scope.isFilter = true;
        });

        $scope.getColumns = function () {
            return [
                {
                    'name' : $scope.language.no,
                    'isSortable': false,
                    'css': 'col-md-1'
                },
                {
                    'name' : $scope.language.title,
                    'isSortable': true,
                    'attribute': 'title',
                    'css': 'col-md-4'
                },
                {
                    'name' : $scope.language.tags,
                    'isSortable': false,
                    'css': 'col-md-5'
                },
                {
                    'name' : $scope.language.date,
                    'isSortable': true,
                    'attribute': 'addedDate',
                    'css': 'col-md-2'
                }
            ];
        };

        $scope.getData = function () {
            return articleService.getArticles();
        };

        $scope.httpSuccess = function (data, totalElements) {
            $scope.articles = data;
            $scope.totalElements = totalElements;
        };

        $scope.httpFailure = function (response) {
            console.log(response);
        };

    });