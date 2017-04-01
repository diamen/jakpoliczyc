angular.module('jakPoliczycDirectives')
    .directive('jpHasRoles', ['$compile', 'jpauth', function ($compile, jpauth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function (elem, attr) {
                var roles = [];
                var input = attr.jpHasRoles.replace(/ /g, '');
                input.indexOf(',') != -1 ? roles = input.split(',') : roles.push(input);

                console.log(attr);
                console.log(roles);

                elem.removeAttr('jp-has-roles');
                elem.attr('ng-show', 'show');
                var fn = $compile(elem);

                return function (scope) {
                    scope.show = false;
                    fn(scope);

                    var hasRoles = function (r) {
                        scope.show = jpauth.hasRoles(r);
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