angular.module('jakPoliczycDirectives')
    .directive('jpKahootForm', ['kahootService', function (kahootService) {
        return {
            restrict: 'E',
            scope: {
                ngModel: '='
            },
            templateUrl: 'views/templates/jp-kahoot-form.html',
            link: function (scope) {
                kahootService.getKahoots().then(function success(response) {
                    if (response.data) {
                        scope.kahoots = response.data;
                    }
                });

                scope.$watch('kahootEnabled', function (value) {
                    if (!value) {
                        scope.ngModel = undefined;
                    }
                });

                var clearWatch = scope.$watch('ngModel', function (value) {
                    if (value) {
                        scope.kahootEnabled = true;
                        clearWatch();
                    }
                });

            }
        }
    }]);