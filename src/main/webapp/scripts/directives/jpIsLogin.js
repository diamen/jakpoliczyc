angular.module('jakPoliczycDirectives')
    .directive('jpIsLogin', ['$compile', 'jpauth', function ($compile, jpauth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function () {
                return function (scope, element) {
                    scope.login = false;
                    element.removeAttr('jp-is-login');
                    element.attr('ng-show', 'login');
                    $compile(element)(scope);

                    if (jpauth.isLogin()) {
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