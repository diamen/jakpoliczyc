angular.module('jakPoliczycDirectives')
    .directive('jpmenu', function () {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                attrs.$observe('items', function (data) {
                    if (data.length > 0) {
                        scope.nitems = angular.fromJson(data);
                    }
                }, true);
                scope.collapsed = true;
                scope.selected = false;
                var parentElem = element.find('i');

                scope.toggleAll = function () {
                  scope.collapsed ?
                      scope.$broadcast('angular-ui-tree:expand-all') : scope.$broadcast('angular-ui-tree:collapse-all');
                  parentElem.toggleClass('fa-caret-square-o-down');
                  parentElem.toggleClass('fa-caret-square-o-up');
                  scope.collapsed = !scope.collapsed;
                };

                scope.select = function (that, item) {
                    var temp = that.selected;
                    unselectAll();
                    that.selected = !temp;
                    that.selected ? scope.$emit('menu-up', item) : scope.$emit('menu-up', undefined);

                    closeMenu();
                };

                scope.unselectAll = function () {
                    unselectAll();
                };

                scope.closeMenu = function () {
                    closeMenu();
                };

                function unselectAll() {
                    scope.$broadcast('unselect-down');
                }

                function closeMenu() {
                    scope.$emit('close-up');
                }
            },
            template:

            "<section style='display: table;' class='row-s'>" +
                "<button ng-click='unselectAll()' class='btn btn-default col-s-4 show-s'>{{$root.language.clear}}</button>" +
                "<button ng-click='toggleAll(); toggle =! toggle' class='btn btn-default col-s-4 show-s'>" +
                    "<span ng-hide='toggle'>{{$root.language.expand}}</span>" +
                    "<span ng-show='toggle'>{{$root.language.collapse}}</span>" +
                "</button>" +
                "<button ng-click='closeMenu()' class='btn btn-default col-s-4 show-s'>{{$root.language.close}}</button>" +
            "</section>" +

            "<div class='jpmenutitle hide-s' ng-click='toggleAll()'>" +
                "<i id='jpmenuparent' class='fa fa-caret-square-o-down' " +
                "aria-hidden='true'></i>" +
                "MENU" +
            "</div>" +

            "<div id='tree-root' class='jpmenu' ui-tree data-drag-enabled='false'>" +
                "<ol ui-tree-nodes='' ng-model='nitems'>" +
                    "<li ng-repeat='item in nitems track by $index' ui-tree-node ng-include=\"'views/templates/jpnode.html'\">" +
                    "</li>" +
                "</ol>" +
            "</div>"
        };

    });
