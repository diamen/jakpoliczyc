angular.module('jakPoliczycDirectives')
    .directive('jppostarea', [function () {
        return {
            restrict: 'E',
            scope: false,
            templateUrl: 'views/templates/jppostarea.html',
            link: function(scope) {
                scope.add.content = "";

                scope.appendLatex = function() {
                    append("<latex></latex>");
                };

                scope.appendPhoto = function () {
                    append("<photo>Wklej linka</photo>");
                };

                scope.preview = function() {
                    scope.show = true;

                    if(scope.show)
                        scope.invokeParse(scope.add.content);
                };

                function append(value) {
                    scope.add.content += value;
                }

            }
        };

    }]);