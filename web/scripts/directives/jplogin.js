angular.module('jakPoliczycDirectives')
    .directive('jpLogin', ['$timeout', '$window', function($timeout, $window) {
        return {
            restrict: 'E',
            scope: {
                loginId: '@'
            },
            link: function(scope, element) {

                var _top,
                    _elemHeight,
                    _left;
                var _login = document.querySelector(scope.loginId);
                var _loginElement = angular.element(_login);

                (function init() {
                    _elemHeight = element[0].offsetHeight;
                    element.css('top', '-' + _elemHeight + 'px');
                }());

                _loginElement.bind('click', function () {
                    move();
                    _top = _top || _loginElement.parent()[0].offsetHeight;
                    toggleProperty();
                });

                $timeout(angular.element($window).bind('resize', function() {
                    clear();
                }), 100);

                function move() {
                    _left = _left || _login.offsetLeft + _login.offsetWidth - element[0].offsetWidth;
                    element.css('left', _left + 'px');
                }

                function toggleProperty() {
                    var top = element.css('top') || '0px';
                    var topValue = +top.substr(0, top.length - 2);

                    var value = topValue > 0 ? -_elemHeight : _top;
                    element.css('top', value + 'px');
                }

                function clear() {
                    element.css('left', '0px');
                    element.css('top', '-' + _elemHeight + 'px');
                    _left = undefined;
                    _top = undefined;
                }

            },
            templateUrl: '/views/templates/jplogin.html'
        }
    }]);