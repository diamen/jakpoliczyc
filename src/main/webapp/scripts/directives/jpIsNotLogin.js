angular.module('jakPoliczycDirectives')
    .directive('jpIsNotLogin', ['$compile', function ($compile) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function () {
                return function (scope, element) {
                    element.removeAttr('jp-is-not-login');
                    element.attr('jp-is-login', 'false');
                    $compile(element)(scope);
                }
            }
        }
    }]);