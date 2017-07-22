angular.module('jakPoliczycDirectives')
    .directive('jpPendingButton', ['jpLabelService', function (jpLabelService) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                pendingLabelKey: '=',
                enabledLabelKey: '=',
                jpDisabled: '=',
                jpPending: '=',
                jpClass: '@',
                jpType: '@'
            },
            link: function (scope, element, attrs) {
                scope.pending = false;
                scope.disabled = false;
                scope.enabledLabel = jpLabelService.getLabel(attrs.enabledLabelKey || 'send');
                scope.pendingLabel = jpLabelService.getLabel(attrs.pendingLabelKey || 'sending');

                var jpType = attrs.jpType || 'submit';
                var jpClass = attrs.jpClass || 'btn btn-default';

                element.attr('type', jpType);
                element.attr('class', jpClass);

                scope.$watch('jpPending', function (newVal) {
                    scope.pending = newVal;
                });

                scope.$watch('jpDisabled', function (newVal) {
                    scope.disabled = newVal;
                });
            },
            templateUrl: 'views/templates/jp-pending-button.html'
        }
    }]);