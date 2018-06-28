angular.module('jakPoliczycDirectives')
    .directive('jpPdfForm', function () {
        return {
            restrict: 'E',
            scope: {
                ngModel: '='
            },
            link: function (scope) {

                scope.$watch('pdfEnabled', function (value) {
                    if (!value) {
                        scope.ngModel = undefined;
                    }
                });

                var clearWatch = scope.$watch('ngModel', function (value) {
                    if (value && value.length > 1) {
                        scope.pdfEnabled = true;
                        clearWatch();
                    }
                });
            },
            template:

            '<div ng-form name="pdfForm" class="input-group add-youtube">' +
                '<span class="input-group-addon">' +
                    '<input ng-model="pdfEnabled" type="checkbox" aria-label="pdf checkbox">' +
                '</span>' +
                '<span class="input-group-addon" id="sizing-addon1">www</span>' +
                '<input ng-disabled="!pdfEnabled" placeholder="pdf link" class="form-control" aria-label="pdf input" name="pdf" ng-model="ngModel" ng-required="pdfEnabled" jppdf>' +
            '</div>' +
            '<div ng-show="pdfForm.pdf.$error.required">' +
                '<span class="jperror">{{$root.language.errReq}}</span>' +
            '</div>' +
            '<div ng-show="pdfForm.pdf.$error.jppdf">' +
                '<span class="jperror">{{$root.language.errPdf}}</span>' +
           '</div>'
        }
    });