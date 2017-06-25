angular.module('jakPoliczycDirectives')
    .directive('jpIsLogin', ['$compile', 'jpAuth', function ($compile, jpAuth) {
        return {
            restrict: 'A',
            priority: 1001,
            terminal: true,
            scope: true,
            compile: function () {
                return function (scope, element, attr) {
                    var jpIsLogin = !(attr.jpIsLogin === "false");
                    var angularDirective = jpIsLogin ? 'ng-show' : 'ng-hide';

                    scope.login = false;
                    var attrs = ['login'];
                    var ngShowValue = element.attr('ng-show');
                    if (ngShowValue) {
                        attrs.push(ngShowValue);
                    }
                    var ngHideValue = element.attr('ng-hide');
                    if (ngHideValue) {
                        attrs.push("!" + ngHideValue);
                    }

                    element.removeAttr('jp-is-login');
                    element.removeAttr(angularDirective);
                    element.attr(angularDirective, attrs.join(" && "));
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