angular.module('jakPoliczycDirectives')
    .directive('jpLogin', ['$timeout', '$window', 'jpauth', function($timeout, $window, jpauth) {
        return {
            restrict: 'E',
            scope: {
                addRedirect: '=',
                storageRedirect: '=',
                isAdmin: '=',
                loginId: '@'
            },
            link: function(scope, element) {
                var _top,
                    _elemHeight,
                    _left;
                var _login = document.querySelector(scope.loginId);
                var _loginElement = angular.element(_login);
                var _mouseenter = false;
                var _expanded = false;

                (function init() {
                    _elemHeight = element[0].offsetHeight;
                    element.css('top', '-' + _elemHeight + 'px');
                }());

                _loginElement.bind('click', function () {
                    move();
                    _top = _top || _loginElement.parent()[0].offsetHeight;
                    toggleProperty();
                    wait();
                });

                element.children().on('mouseenter', function () {
                   _mouseenter = true;
                });

                element.children().on('mouseleave', function () {
                    wait();
                    _mouseenter = false;
                });

                $timeout(angular.element($window).bind('resize', function() {
                    clear();
                }), 100);

                scope.login = function(username, password) {
                   jpauth.login(username, password, function (response) {
                      scope.isAdmin = response;
                   });
                };

                scope.logout = function () {
                  jpauth.logout(function (response) {
                     scope.isAdmin = response;
                  });
                };

                function move() {
                    _left = _left || _login.offsetLeft + _login.offsetWidth - element[0].offsetWidth;
                    element.css('left', _left + 'px');
                }

                function toggleProperty() {
                    var top = element.css('top') || '0px';
                    var topValue = +top.substr(0, top.length - 2);

                    var value = topValue > 0 ? -_elemHeight : _top;
                    element.css('top', value + 'px');
                    _expanded = topValue < 0;
                }

                function clear() {
                    element.css('left', '0px');
                    element.css('top', '-' + _elemHeight + 'px');
                    _left = undefined;
                    _top = undefined;
                }

                function wait() {
                    $timeout(function () {
                        if (_expanded && !_mouseenter) {
                            toggleProperty();
                        }
                    }, 3000)
                }
            },
            templateUrl: 'views/templates/jplogin.html'
        }
    }]);