angular.module('jakPoliczycDirectives')
    .directive('jpurls', function() {
        return {
            require: 'ngModel',
            link: function(scope, elm, attrs, ctrl) {
                var urlRegex = new RegExp('^((?!http:\/\/)(?!https:\/\/)(?!www\.).)*$', "im");

                ctrl.$parsers.unshift(function (modelValue) {

                        if (ctrl.$isEmpty(modelValue)) {
                            ctrl.$setValidity('jpurls', true);
                            return modelValue;
                        }

                        if (urlRegex.test(modelValue)) {
                            ctrl.$setValidity('jpurls', true);
                            return modelValue;
                        } else {
                            ctrl.$setValidity('jpurls', false);
                            return modelValue;
                        }
                });

            }
        };
    });