angular.module('jakPoliczycDirectives')
    .directive('jpToggle', ['$timeout', function($timeout) {
        return {
            restrict: 'A',
            scope: {},
            link: function(scope, element, attrs) {
                var _id = attrs.jpToggle;
                var _target;
                var _display;

                $timeout(function () {
                    _target = angular.element(document.querySelector(_id));
                    _display = _target.css('display') || 'inherit';
                }, 0);

                element.bind('click', function() {
                    toggle();
                });

                function toggle() {
                    if (_target.css('display') === 'none') {
                        _target.css('display', _display);
                    } else {
                        _target.css('display', 'none');
                    }
                }

            }
        }
    }]);