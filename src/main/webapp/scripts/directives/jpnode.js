angular.module('jakPoliczycDirectives')
    .directive('jpnode', function () {
        return {
            restrict: 'A',
            link: function (scope) {
                scope.$on('unselect-down', function () {
                    scope.selected = false;
                });
            }
        }
    });