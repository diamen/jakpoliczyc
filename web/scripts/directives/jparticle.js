angular.module('jakPoliczycDirectives')
    .directive('jparticle', ['$sanitize', 'jpparser', 'jpvalidator', function ($sanitize, jpparser, jpvalidator) {
        return {
            restrict: 'E',
            scope: {
                title: '@',
                author: '@',
                date: '@',
                tags: '@',
                intro: '@',
                content: '@',
                comments: '@',
                add: '&'
            },
            templateUrl: '/views/templates/article.html',
            link: function($scope) {
                $scope.pattern = {};
                $scope.pattern.user = "[a-zA-Z0-9]+";
                $scope.pattern.content = new RegExp('^((?!http:\/\/)(?!https:\/\/)(?!www\.).)*$', "im");
                $scope.ncomments = angular.fromJson($scope.comments);
                $scope.ntags = angular.fromJson($scope.tags);
                $scope.ncontents = jpparser.parseTextBlock($scope.content);
                $scope.noturls = jpvalidator.noturls;

                $scope.nadd = function (author, content) {
                  $scope.add({author: author, content: $sanitize(content)});
                };
            }
        };

    }]);
