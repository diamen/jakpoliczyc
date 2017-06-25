angular.module('jakPoliczycDirectives')
    .directive('jpalphanumeric', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var charRegex = /^[0-9a-zA-ZąęćłśńźóżĄĘĆŚŁŃŹÓŻ\s]*$/;

                ctrl.$parsers.unshift(function (modelValue) {

                    if (ctrl.$isEmpty(modelValue)) {
                        ctrl.$setValidity('jpalphanumeric', true);
                        return modelValue;
                    }

                    if (charRegex.test(modelValue)) {
                        ctrl.$setValidity('jpalphanumeric', true);
                        return modelValue;
                    } else {
                        ctrl.$setValidity('jpalphanumeric', false);
                        return modelValue;
                    }
                });

            }
        };
    });