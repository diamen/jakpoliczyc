angular.module('jakPoliczycDirectives')
    .directive('jparticle', ['parser', function (parser) {
        return {
            restrict: 'E',
            scope: {
                title: '@',
                author: '@',
                date: '@',
                tags: '@',
                intro: '@',
                content: '@',
                comments: '@'
            },
            templateUrl: '/views/templates/article.html',
            link: function($scope) {
                $scope.ncomments = angular.fromJson($scope.comments);
                $scope.ntags = angular.fromJson($scope.tags);
                $scope.ncontents = parser.parseTextBlock($scope.content);
            }
        };

    }]);
