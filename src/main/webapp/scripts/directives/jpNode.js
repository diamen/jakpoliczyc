angular.module('jakPoliczycDirectives')
    .directive('jpNode', function () {
        return {
            restrict: 'A',
            link: function (scope) {
                scope.$on('unselect-down', function () {
                    scope.selected = false;
                });
            }
        }
    });