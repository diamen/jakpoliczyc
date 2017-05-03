angular.module('jakPoliczycDirectives')
    .directive('jpYoutubeForm', function () {
        return {
            restrict: 'E',
            scope: {
                ngModel: '='
            },
            template:

            '<div ng-form name="ytForm" class="input-group add-youtube">' +
                '<span class="input-group-addon">' +
                    '<input ng-model="youtubeEnabled" type="checkbox" aria-label="youtube checkbox">' +
                '</span>' +
                '<span class="input-group-addon" id="sizing-addon1">www</span>' +
                '<input ng-disabled="!youtubeEnabled" type="url" placeholder="youtube link" class="form-control" aria-label="youtube input" name="youtube" ng-model="ngModel" ng-required="youtubeEnabled" jpyoutube>' +
            '</div>' +
            '<div ng-show="ytForm.youtube.$error.required">' +
                '<span class="jperror">{{$root.language.errReq}}</span>' +
            '</div>' +
            '<div ng-show="ytForm.youtube.$error.jpyoutube">' +
                '<span class="jperror">{{$root.language.errYoutube}}</span>' +
           '</div>'
        }
    });