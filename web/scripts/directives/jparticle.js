angular.module('jakPoliczycDirectives')
    .directive('jparticle', ['$sanitize', '$sce', '$window', 'jpparser', 'jpvalidator', function ($sanitize, $sce, $window, jpparser, jpvalidator) {
        return {
            restrict: 'E',
            scope: {
                article: '@',
                add: '&'
            },
            templateUrl: '/views/templates/jparticle.html',
            link: function($scope, $element) {
                var editables = [];
                $scope.isEditable = false;

                $scope.article = angular.fromJson($scope.article);
                $scope.nauthor = "Małgorzata Kotarska";
                $scope.ndate = $scope.article.date;
                $scope.nintro = $scope.article.intro;
                $scope.ntitle = $scope.article.title;
                $scope.pattern = {};
                $scope.pattern.user = "[a-zA-Z0-9]+";
                $scope.pattern.content = new RegExp('^((?!http:\/\/)(?!https:\/\/)(?!www\.).)*$', "im");
                $scope.ncomments = angular.fromJson($scope.article.comments);
                $scope.ntags = angular.fromJson($scope.article.tags);
                $scope.ncontent = $sce.trustAsHtml($scope.article.content);
                $scope.noturls = jpvalidator.noturls;

                $scope.nadd = function (author, content) {
                  $scope.add({author: author, content: $sanitize(content)});
                };

                $scope.submit = function () {
                  // TODO
                };

                $scope.edit = function () {
                    $scope.isEditable = true;

                    if (editables.length === 0) {
                        angular.forEach($element[0].querySelectorAll('[contenteditable="false"'), function (raw) {
                            editables.push(angular.element(raw));
                        });
                    }
                    toggleEditable(true);
                };

                $scope.cancel = function () {
                  $window.location.reload();
                };

                $scope.remove = function () {
                    // TODO
                };

                function toggleEditable(state) {
                    angular.forEach(editables, function (editable) {
                        editable.attr('contenteditable', state);
                    });
                }
            }
        };

    }]);
