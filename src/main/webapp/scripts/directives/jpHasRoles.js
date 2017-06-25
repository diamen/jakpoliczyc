angular.module('jakPoliczycDirectives')
    .directive('jpHasRoles', ['$compile', 'jpAuth', function ($compile, jpAuth) {
        return {
            restrict: 'A',
            terminal: true,
            priority: 1000,
            scope: true,
            compile: function (elem, attr) {
                var roles = [];
                var input = attr.jpHasRoles.replace(/ /g, '');
                input.indexOf(',') != -1 ? roles = input.split(',') : roles.push(input);

                return function (scope, element) {
                    scope.show = false;
                    element.removeAttr('jp-has-roles');
                    element.attr('ng-show', 'show');
                    $compile(element)(scope);

                    var hasRoles = function (r) {
                        scope.show = jpAuth.hasRoles(r);
                    };
                    hasRoles(roles);

                    scope.$on("login-down", function () {
                        hasRoles(roles);
                    });

                    scope.$on("logout-down", function () {
                        scope.show = false;
                    });
                }
            }
        }
    }]);