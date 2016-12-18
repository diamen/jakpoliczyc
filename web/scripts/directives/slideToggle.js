angular.module('jakPoliczycDirectives')
    .directive('slideToggle', ['$timeout', '$window', function($timeout, $window) {
        return {
            restrict: 'A',
            require: '?ngModel',
            scope: {
              mainId: '@',
              invoke: '='
            },
            link: function(scope, element, attrs, ngModel) {

                var _main,
                    _targets;

                $timeout(function () {
                    _main = document.querySelector(scope.mainId);
                    _targets = document.querySelectorAll(attrs.slideToggle);
                }, 0);

                $timeout(angular.element($window).bind('resize', function() {
                    ngModel.$viewValue = false;
                    move(_main, _targets);
                }), 100);

                scope.$watch('invoke', function (toexpand) {
                    move(_main, _targets, toexpand);
                });

                element.bind('click', function() {
                    move(_main, _targets);
                });

                function move(main, targets, toexpand) {
                    var expanded = angular.isDefined(toexpand) ? toexpand : ngModel.$viewValue ? ngModel.$viewValue : false;
                    var height = angular.element(main).prop('offsetHeight');

                    angular.forEach(targets, function (target) {
                        target.style.top = expanded ? '-' + height + 'px' : '0px';
                    });

                    ngModel.$setViewValue(!expanded);
                }

            }
        }
    }]);