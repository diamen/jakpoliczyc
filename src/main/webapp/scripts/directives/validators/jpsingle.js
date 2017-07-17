angular.module('jakPoliczycDirectives')
    .directive('jpsingle', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var singleRegex = new RegExp(/\b(\w+)\b(?=.*\b\1\b)/igm);

                ctrl.$parsers.unshift(function (modelValue) {

                    if (ctrl.$isEmpty(modelValue)) {
                        ctrl.$setValidity('jpsingle', true);
                        return modelValue;
                    }

                    if (!singleRegex.test(modelValue)) {
                        ctrl.$setValidity('jpsingle', true);
                        return modelValue;
                    } else {
                        ctrl.$setValidity('jpsingle', false);
                        return modelValue;
                    }
                });

            }
        };
    });