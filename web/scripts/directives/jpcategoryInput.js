angular.module('jakPoliczycDirectives')
    .directive('jpcategoryInput', ['jpgenerator', function (jpgenerator) {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                var parentId = attrs.parentId;
                scope.inputModel = '';
                scope.name = jpgenerator.randomString();

                scope.add = function () {
                    scope.addInput(scope.inputModel, element, parentId);
                };

                scope.rem = function () {
                  scope.remove(element);
                };
            },
            template:

            '<div ng-form name="categoryInput" class="form-group">' +
                '<label>Poddział</label>' +
                '<div class="input-group">' +
                    '<input name="{{name}}" class="form-control" ng-model="inputModel" required jpalphanumeric/>' +
                    '<span class="input-group-btn"><button ng-click="rem()" class="btn btn-default remove">Usuń</button></span>' +
                    '<span class="input-group-btn"><button ng-click="add()" ng-disabled="categoryInput[{{\'name\'}}].$error.required || categoryInput[{{\'name\'}}].$error.jpalphanumeric" class="btn btn-default">Dodaj</button></span>' +
                '</div>' +
                '<div ng-show="categoryInput[{{\'name\'}}].$error.required">' +
                    '<span class="jperror">Pole jest wymagane</span>' +
                '</div>' +
                '<div ng-show="categoryInput[{{\'name\'}}].$error.jpalphanumeric">' +
                    '<span class="jperror">Dopuszczalne jedynie znaki alfanumeryczne</span>' +
                '</div>' +
            '</div>'
        };

    }]);
