angular.module('jakPoliczycDirectives')
    .directive('jpIsNotLogin', ['$compile', 'jpAuth', function ($compile, jpAuth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function () {
                return function (scope, element) {
                    scope.login = false;
                    element.removeAttr('jp-is-not-login');
                    element.attr('ng-hide', 'login');
                    $compile(element)(scope);

                    if (jpAuth.isLogin()) {
                        scope.login = true;
                    }

                    scope.$on("login-down", function () {
                        scope.login = true;
                    });

                    scope.$on("logout-down", function () {
                        scope.login = false;
                    });
                }
            }
        }
    }]);