angular.module('jakPoliczycDirectives')
    .directive('jparticle', ['$sanitize', 'jpparser', 'jpvalidator', function ($sanitize, jpparser, jpvalidator) {
        return {
            restrict: 'E',
            scope: {
                article: '@',
                add: '&'
            },
            templateUrl: '/views/templates/jparticle.html',
            link: function($scope) {
                $scope.article = angular.fromJson($scope.article);
                $scope.nauthor = "Małgorzata Kotarska";
                $scope.ndate = $scope.article.date;
                $scope.ntitle = $scope.article.title;
                $scope.pattern = {};
                $scope.pattern.user = "[a-zA-Z0-9]+";
                $scope.pattern.content = new RegExp('^((?!http:\/\/)(?!https:\/\/)(?!www\.).)*$', "im");
                $scope.ncomments = angular.fromJson($scope.article.comments);
                $scope.ntags = angular.fromJson($scope.article.tags);
                $scope.ncontents = jpparser.parseTextBlock($scope.article.content);
                $scope.noturls = jpvalidator.noturls;

                $scope.nadd = function (author, content) {
                  $scope.add({author: author, content: $sanitize(content)});
                };
            }
        };

    }]);
