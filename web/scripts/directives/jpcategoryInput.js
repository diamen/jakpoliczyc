angular.module('jakPoliczycDirectives')
    .directive('jpcategoryInput', function () {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                var parentId = attrs.parentId;
                scope.inputModel = '';

                scope.add = function () {
                    scope.error = scope.addInput(scope.inputModel, element, parentId);
                };

                scope.rem = function () {
                  scope.remove(element);
                };
            },
            template:

            '<div class="form-group">' +
                '<label>Poddział</label>' +
                '<div class="input-group">' +
                    '<input class="form-control" ng-model="inputModel"/>' +
                    '<span class="input-group-btn"><button ng-click="rem()" class="btn btn-default remove">Usuń</button></span>' +
                    '<span class="input-group-btn"><button ng-click="add()" class="btn btn-default">Dodaj</button></span>' +
                '</div>' +
                '<small ng-show="error" class="form-text jperror">Wypełnij!</small>' +
            '</div>'
        };

    });
