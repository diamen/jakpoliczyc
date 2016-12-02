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

                var main = document.querySelector(scope.mainId);
                var targets = document.querySelectorAll(attrs.slideToggle);

                $timeout(angular.element($window).bind('resize', function() {
                    ngModel.$viewValue = false;
                    move(main, targets);
                }), 50);

                scope.$watch('invoke', function (toexpand) {
                    move(main, targets, toexpand);
                });

                element.bind('click', function() {
                    move(main, targets);
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