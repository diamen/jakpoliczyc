angular.module('jakPoliczycDirectives')
    .directive('jpcharacters', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var charRegex = /^[a-zA-ZąęćśńźóżĄĘĆŚŃŹÓŻ\s]*$/;

                ctrl.$parsers.unshift(function (modelValue) {

                        if (ctrl.$isEmpty(modelValue)) {
                            ctrl.$setValidity('jpcharacters', true);
                            return modelValue;
                        }

                        if (charRegex.test(modelValue)) {
                            ctrl.$setValidity('jpcharacters', true);
                            return modelValue;
                        } else {
                            ctrl.$setValidity('jpcharacters', false);
                            return modelValue;
                        }
                });

            }
        };
    });