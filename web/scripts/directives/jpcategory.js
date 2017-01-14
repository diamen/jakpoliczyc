angular.module('jakPoliczycDirectives')
    .directive('jpcategory', ['$compile', function ($compile) {
        return {
            restrict: 'E',
            scope: {
                items: '@'
            },
            link: function(scope, element, attrs) {
                scope.nitems = angular.fromJson(scope.items);
                scope.editable = false;
                scope.parentId = attrs.parentId || 0;

                scope.edit = function () {
                    scope.editable = !scope.editable;
                };

                scope.addInput = function(inputModel, inputElem, parentId) {
                    var text = inputModel || scope.inputModel;

                    if (!text || text.length === 0) {
                        return true;
                    }

                    scope.disable(inputElem);

                    var id = parentId || 0;
                    var elem = inputElem || element;
                    var html = "<jpcategory-input parent-id='";
                    html += id;
                    html += "'></jpcategory-input>";

                    elem.children().append($compile(html)(scope))
                };

                scope.addSelect = function() {
                    var item = angular.fromJson(scope.selectedModel) || {};

                    var hasChild = (angular.isDefined(item.submenus) && item.submenus.length > 0);
                    var hasChild = scope.editable ? false : hasChild;

                    scope.disable();

                    if (!hasChild) {
                        scope.addInput("not empty");
                        return;
                    }

                    var html = "<jpcategory items='";
                    html += angular.toJson(item.submenus);
                    html += "' parent-id='";
                    html += item.id;
                    html += "'></jpcategory>";

                    element.children().append($compile(html)(scope))
                };

                scope.enable = function (input) {
                  toggle(input, true);
                };

                scope.disable = function(input) {
                    toggle(input, false);
                };

                scope.remove = function (el) {
                  var elem =  el || element;
                  scope.enable(elem.parent().parent());
                  elem.remove();
                };

                function toggle(html, state) {
                    var elem = html || element;
                    state = !state;
                    elem.find('select').attr("disabled", state);
                    elem.find('input').attr("disabled", state);
                    angular.forEach(elem.find("button"), function (el) {
                        var e = angular.element(el);
                        if (!e.hasClass('remove'))
                            angular.element(el).attr("disabled", state);
                    });
                }

            },
            template:

            '<div ng-form name="category" class="form-group">' +
                '<label>{{ parentId > 0 ? "Poddział" : "Dział"}}</label>' +
                '<div class="input-group">' +
                    '<input name="input" ng-if="editable" ng-model="inputModel" class="form-control" required jpalphanumeric/>' +
                    '<select ng-hide="editable" ng-model="selectedModel" class="form-control jpzindexfix">' +
                        '<option value="" ng-show="false"></option>' +
                        '<option ng-selected="nitems[0]" ng-repeat="item in nitems" value="{{item}}">{{item.name}}</option>' +
                    '</select>' +
                    '<span class="input-group-btn"><button ng-click="edit()" class="btn btn-default">{{ editable ? "Anuluj" : "Edytuj" }}</button></span>' +
                    '<span ng-show="parentId > 0" class="input-group-btn"><button ng-click="remove()" class="btn btn-default remove">Usuń</button></span>' +
                    '<span ng-hide="editable" class="input-group-btn"><button ng-click="addSelect()" class="btn btn-default">Dodaj</button></span>' +
                    '<span ng-show="editable" class="input-group-btn"><button ng-click="addInput()" ng-disabled="category.input.$error.required || category.input.$error.jpalphanumeric" class="btn btn-default">Dodaj</button></span>' +
                '</div>' +
                '<div ng-show="editable && category.input.$error.required">' +
                    '<span class="jperror">Pole jest wymagane</span>' +
                '</div>' +
                '<div ng-show="editable && category.input.$error.jpalphanumeric">' +
                    '<span class="jperror">Dopuszczalne jedynie znaki alfanumeryczne</span>' +
                '</div>' +
            '</div>'
        };

    }]);
