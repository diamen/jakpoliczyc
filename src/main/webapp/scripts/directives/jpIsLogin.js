angular.module('jakPoliczycDirectives')
    .directive('jpIsLogin', ['$compile', 'jpauth', function ($compile, jpauth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function (elem) {
                elem.removeAttr('jp-is-login');
                elem.attr('ng-show', 'login');
                var fn = $compile(elem);
                return function (scope) {
                    scope.login = false;
                    fn(scope);

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