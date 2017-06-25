angular.module('jakPoliczycDirectives')
    .directive('jpPostarea', ['$rootScope', function ($rootScope) {
        return {
            restrict: 'E',
            scope: false,
            templateUrl: 'views/templates/jp-postarea.html',
            link: function(scope) {
                scope.add = {};
                scope.add.content = "";

                scope.appendLatex = function() {
                    append("[latex]" + $rootScope.language.codeLatex + "[/latex]");
                };

                scope.appendPhoto = function () {
                    append("[photo]" + $rootScope.language.codePhoto + "[/photo]");
                };

                scope.preview = function() {
                    scope.show = true;

                    if(scope.show)
                        scope.invokeParse(scope.add.content);
                };

                function append(value) {
                    scope.add.content = scope.add.content || "";
                    scope.add.content += value;
                }

            }
        };

    }]);