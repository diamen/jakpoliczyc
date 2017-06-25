angular.module('jakPoliczycDirectives')
    .directive('jpLogout', ['$compile', 'jpAuth', function ($compile, jpAuth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function (elem) {
                elem.removeAttr('jp-logout');
                elem.attr('ng-click', 'logout()');
                var fn = $compile(elem);
                return function (scope) {
                    scope.logout = function () {
                        jpAuth.logout(function () {
                            scope.$emit("logout-up");
                            scope.goHome();
                        });
                    };
                    fn(scope);
                }
            }
        }
    }]);