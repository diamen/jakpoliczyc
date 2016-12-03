angular.module('jakPoliczycDirectives')
    .directive('jparticle', ['$sanitize', 'parser', function ($sanitize, parser) {
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
                $scope.ncontents = parser.parseTextBlock($scope.content);

                $scope.nadd = function (author, content) {
                  $scope.add({author: author, content: $sanitize(content)});
                };

                $scope.noturls = function(value) {
                    return angular.isDefined(value) ?
                        (value.includes("www.") || value.includes("http://") || value.includes("https://")) : false;
                };
            }
        };

    }]);
