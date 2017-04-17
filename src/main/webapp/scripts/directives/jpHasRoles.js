angular.module('jakPoliczycDirectives')
    .directive('jpHasRoles', ['$compile', 'jpauth', function ($compile, jpauth) {
        return {
            restrict: 'A',
            scope: true,
            compile: function (elem, attr) {
                var roles = [];
                var input = attr.jpHasRoles.replace(/ /g, '');
                input.indexOf(',') != -1 ? roles = input.split(',') : roles.push(input);

                console.log(attr);
                console.log(roles);

                return function (scope, element) {
                    scope.show = false;
                    element.removeAttr('jp-has-roles');
                    element.attr('ng-show', 'show');
                    $compile(element)(scope);

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