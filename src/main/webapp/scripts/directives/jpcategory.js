angular.module('jakPoliczycDirectives')
    .directive('jpcategory', ['$compile', 'jpstorage', function ($compile, jpstorage) {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                scope.nitems = angular.fromJson(attrs.items) || [];
                scope.editable = false;
                scope.parentId = attrs.parentId || 0;

                scope.$on('publish-down', function () {
                    if (angular.isUndefined(scope.editable))
                        return;

                    var name = '';
                    var id = 0;
                    if (scope.editable) {
                        name = scope.inputModel;
                    } else {
                        id = scope.selectedModel.id;
                        name = scope.selectedModel.name;
                    }
                    scope.saveMenuInfo(id, name);
                });

                scope.edit = function () {
                    scope.editable = !scope.editable;
                };

                scope.saveMenuInfo = function (id, name) {
                    var menu = new Menu(id, name);
                    var menus = jpstorage.retrieve("menus") || [];
                    menus.push(menu);
                    jpstorage.put("menus", menus);
                    console.log("id = " + id + " | name = " + name);
                    scope.$emit('publish-up');
                };

                scope.addInput = function(inputModel, inputElem, parentId) {
                    var text = inputModel || scope.inputModel;

                    if (!text || text.length === 0) {
                        return;
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
                        scope.addInput("not empty", undefined, item.id);
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

                element.on('$destroy', function () {
                   scope = {};
                });

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

            '<div ng-if="nitems" ng-form name="category" class="form-group">' +
                '<label>{{ parentId > 0 ? $root.language.subchapter : $root.language.chapter }}</label>' +
                '<div class="input-group">' +
                    '<input name="input" ng-show="editable" ng-model="inputModel" class="form-control" ng-required="editable" jpalphanumeric/>' +
                    '<select name="selNames" id="selNames" class="form-control jpzindexfix" ng-hide="editable" ' +
                    'ng-options="item.name for item in nitems track by item.id" ng-model="$parent.selectedModel" ng-init="$parent.selectedModel = nitems[0]">' +
                    '</select>' +
                    '<span class="input-group-btn"><button ng-click="edit()" class="btn btn-default">{{ editable ? $root.language.cancel : $root.language.edit }}</button></span>' +
                    '<span ng-show="parentId > 0" class="input-group-btn"><button ng-click="remove()" class="btn btn-default remove">{{$root.language.remove}}</button></span>' +
                    '<span ng-hide="editable" class="input-group-btn"><button ng-click="addSelect()" class="btn btn-default">{{$root.language.add}}</button></span>' +
                    '<span ng-show="editable" class="input-group-btn"><button ng-click="addInput()" ng-disabled="category.input.$error.required || category.input.$error.jpalphanumeric" class="btn btn-default">{{$root.language.add}}</button></span>' +
                '</div>' +
                '<div ng-show="editable && category.input.$error.required">' +
                    '<span class="jperror">{{$root.language.errReq}}</span>' +
                '</div>' +
                '<div ng-show="editable && category.input.$error.jpalphanumeric">' +
                    '<span class="jperror">{{$root.language.errAlphanum}}</span>' +
                '</div>' +
            '</div>'
        };

    }]);
