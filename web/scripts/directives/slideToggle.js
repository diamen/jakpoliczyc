angular.module('jakPoliczycDirectives')
    .directive('slideToggle', function() {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: function(scope, element, attrs, ngModel) {
                var target = document.querySelector(attrs.slideToggle);

                element.bind('click', function() {
                    var expanded = ngModel.$viewValue ? ngModel.$viewValue : false;
                    var height = angular.element(target).prop('offsetHeight');

                    if(!expanded) {
                        target.style.top = '0px';
                    } else {
                        target.style.top = '-' + height + 'px';
                    }
                    ngModel.$setViewValue(!expanded);
                });
            }
        }
    });