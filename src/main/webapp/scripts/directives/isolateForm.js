angular.module('jakPoliczycDirectives')
    .directive('isolateForm', [function () {
        return {
            restrict: 'A',
            require: '?form',
            link: function (scope, elm, attrs, ctrl) {
                if (!ctrl) {
                    return;
                }

                var ctrlCopy = {};
                angular.copy(ctrl, ctrlCopy);

                var parent = elm.parent().controller('form');
                parent.$removeControl(ctrl);

                var isolatedFormCtrl = {
                    $setValidity: function (validationToken, isValid, control) {
                        ctrlCopy.$setValidity(validationToken, isValid, control);
                        parent.$setValidity(validationToken, true, ctrl);
                    },
                    $setDirty: function () {
                        elm.removeClass('ng-pristine').addClass('ng-dirty');
                        ctrl.$dirty = true;
                        ctrl.$pristine = false;
                    }
                };
                angular.extend(ctrl, isolatedFormCtrl);
            }
        };
}]);