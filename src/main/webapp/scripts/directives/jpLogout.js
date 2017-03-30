angular.module('jakPoliczycDirectives')
    .directive('jpLogout', ['$compile', 'jpauth', function ($compile, jpauth) {
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
                        jpauth.logout(function () {
                            scope.$emit("logout-up");
                        });
                    };
                    fn(scope);
                }
            }
        }
    }]);