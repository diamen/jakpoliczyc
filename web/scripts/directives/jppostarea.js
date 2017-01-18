angular.module('jakPoliczycDirectives')
    .directive('jppostarea', [function () {
        return {
            restrict: 'E',
            scope: {
                rows: '@'
            },
            templateUrl: 'views/templates/jppostarea.html',
            link: function(scope) {
                scope.expression = "";

                scope.appendLatex = function() {
                    append("<latex></latex>");
                };

                scope.appendPhoto = function () {
                    append("<photo>Wklej linka</photo>");
                };

                scope.preview = function() {
                    scope.show = true;

                    if(scope.show)
                        scope.invokeParse(scope.expression);
                };

                function append(value) {
                    scope.expression += value;
                }

            }
        };

    }]);