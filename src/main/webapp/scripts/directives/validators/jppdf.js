angular.module('jakPoliczycDirectives')
    .directive('jppdf', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var urlRegex = new RegExp('^(http:\/\/|https:\/\/|www\.).*(\.pdf)$', "im");

                ctrl.$parsers.unshift(function (modelValue) {

                        if (ctrl.$isEmpty(modelValue)) {
                            ctrl.$setValidity('jppdf', true);
                            return modelValue;
                        }

                        if (urlRegex.test(modelValue)) {
                            ctrl.$setValidity('jppdf', true);
                            return modelValue;
                        } else {
                            ctrl.$setValidity('jppdf', false);
                            return modelValue;
                        }
                });
            }
        };
    });