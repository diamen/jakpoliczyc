angular.module('jakPoliczycDirectives')
    .directive('slideable', function () {
        return {
            restrict:'C',
            compile: function (element, attr) {

                return function postLink(scope, element, attrs) {
                    attrs.duration = (!attrs.duration) ? '1s' : attrs.duration;
                    attrs.easing = (!attrs.easing) ? 'ease-in-out' : attrs.easing;

                    element.css({
                        'overflow': 'hidden',
                        'top': '0px',
                        'transitionProperty': 'top',
                        'transitionDuration': attrs.duration,
                        'transitionTimingFunction': attrs.easing
                    });
                };
            }
        };
    });