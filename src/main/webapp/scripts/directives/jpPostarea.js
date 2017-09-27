angular.module('jakPoliczycDirectives')
    .directive('jpPostarea', ['$rootScope', function ($rootScope) {
        return {
            restrict: 'E',
            scope: {
                model: '='
            },
            templateUrl: 'views/templates/jp-postarea.html',
            link: function(scope) {

                scope.appendLatex = function() {
                    append("[latex]" + $rootScope.language.codeLatex + "[/latex]");
                };

                scope.appendPhoto = function () {
                    append("[photo]" + $rootScope.language.codePhoto + "[/photo]");
                };

                scope.preview = function() {
                    scope.show = true;

                    if(scope.show)
                        scope.invokeParse(scope.model);
                };

                function append(value) {
                    scope.model = scope.model || "";
                    scope.model += value;
                }

            }
        };

    }]);