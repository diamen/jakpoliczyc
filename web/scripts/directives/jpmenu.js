angular.module('jakPoliczycDirectives')
    .directive('jpmenu', function () {
        return {
            restrict: 'E',
            scope: {
                items: '@'
            },
            link: function(scope, element) {
                scope.nitems = angular.fromJson(scope.items);
                scope.collapsed = true;
                var parentElem = element.find('i');

                scope.toggleAll = function () {
                  scope.collapsed ?
                      scope.$broadcast('angular-ui-tree:expand-all') : scope.$broadcast('angular-ui-tree:collapse-all');
                  parentElem.toggleClass('fa-caret-square-o-down');
                  parentElem.toggleClass('fa-caret-square-o-up');
                  scope.collapsed = !scope.collapsed;
                };
            },
            template:
            "<div class='jpmenutitle'>" +
                "<i id='jpmenuparent' class='fa fa-caret-square-o-down' " +
                "aria-hidden='true' ng-click='toggleAll()'></i>" +
                "MENU" +
            "</div>" +

            "<div id='tree-root' class='jpmenu' ui-tree data-drag-enabled='false'>" +
                "<ol ui-tree-nodes='' ng-model='nitems'>" +
                    "<li ng-repeat='item in nitems track by $index' ui-tree-node ng-include=\"'/views/templates/jpnode.html'\">" +
                    "</li>" +
                "</ol>" +
            "</div>"
        };

    });
