angular.module('jakPoliczycDirectives')
    .directive('jpyoutube', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var urlRegex = new RegExp('^(https\:\/\/)?(www\.youtube\.(com|pl)\/embed)\/.+$', "i");

                ctrl.$parsers.unshift(function (modelValue) {

                        if (ctrl.$isEmpty(modelValue)) {
                            ctrl.$setValidity('jpyoutube', true);
                            return modelValue;
                        }

                        if (urlRegex.test(modelValue)) {
                            ctrl.$setValidity('jpyoutube', true);
                            return modelValue;
                        } else {
                            ctrl.$setValidity('jpyoutube', false);
                            return modelValue;
                        }
                });
            }
        };
    });