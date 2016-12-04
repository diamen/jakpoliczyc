angular.module('jakPoliczycDirectives')
    .directive('jpmenu', ['$compile', function ($compile) {
        return {
            restrict: 'E',
            scope: {
                items: '@'
            },
            link: function(scope, element) {
                scope.nitems = angular.fromJson(scope.items);
            },
            template:
            "<div id='tree-root' class='jpmenu' ui-tree data-drag-enabled='false'>" +
                "<ol ui-tree-nodes='' ng-model='nitems' ng-class='{hidden: collapsed}'>" +
                    "<li ng-repeat='item in nitems track by $index' ui-tree-node jpnode item='{{item}}'>" +
                    "</li>" +
                "</ol>" +
            "</div>"
        };

    }]);
