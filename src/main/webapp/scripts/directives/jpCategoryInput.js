angular.module('jakPoliczycDirectives')
    .directive('jpCategoryInput', ['jpGenerator', function (jpGenerator) {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                var uuid = jpGenerator.guid();
                element.attr('uuid', uuid);

                scope.$on('publish-down', function () {
                    if (angular.isUndefined(scope.inputModel))
                        return;
                    scope.saveMenuInfo(0, scope.inputModel);
                });

                element.on('$destroy', function () {
                    scope = {};
                });

                var parentId = attrs.parentId;
                scope.inputModel = '';
                scope.name = jpGenerator.randomString();

                scope.add = function () {
                    scope.addInput(scope.inputModel, element, parentId);
                };

                scope.rem = function () {
                  scope.remove(element);
                };
            },
            template:

            '<div ng-form name="categoryInput" class="form-group">' +
                '<label>{{$root.language.subchapter}}</label>' +
                '<div class="input-group">' +
                    '<input name="{{name}}" class="form-control" ng-model="inputModel" required jpalphanumeric/>' +
                    '<span class="input-group-btn"><button ng-click="rem()" class="btn btn-default remove">{{$root.language.remove}}</button></span>' +
                    '<span class="input-group-btn"><button ng-click="add()" ng-disabled="categoryInput[{{\'name\'}}].$error.required || categoryInput[{{\'name\'}}].$error.jpalphanumeric" class="btn btn-default">{{$root.language.add}}</button></span>' +
                '</div>' +
                '<div ng-show="categoryInput[{{\'name\'}}].$error.required">' +
                    '<span class="jperror">{{$root.language.errReq}}</span>' +
                '</div>' +
                '<div ng-show="categoryInput[{{\'name\'}}].$error.jpalphanumeric">' +
                    '<span class="jperror">{{$root.language.errAlphanum}}</span>' +
                '</div>' +
            '</div>'
        };

    }]);
