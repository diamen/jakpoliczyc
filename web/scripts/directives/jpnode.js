angular.module('jakPoliczycDirectives')
    .directive('jpnode', ['$compile', function ($compile) {
        var linkFunction = function (scope, el, attrs) {
            var contents = el.contents.remove();

            scope.toggle = function () {
                scope.collapsed = !scope.collapsed;
            };

            scope.nitem = angular.fromJson(scope.item);
            console.log(scope.nitem);
            $compile(contents)(scope, function(clone){
                el.append(clone);
            });
        };
        return {
            restrict: 'A',
            scope: {
                item: '@'
            },
            compile: function (el) {
                 var contents = el.contents().remove();

                return function(scope, el){
                    scope.toggle = function () {
                        scope.collapsed = !scope.collapsed;
                    };

                    scope.nitem = angular.fromJson(scope.item);
                    console.log(scope.nitem);
                    $compile(contents)(scope, function(clone){
                        el.append(clone);
                    });
                };
            },
            template:
            "<div class='node' ui-tree-handle>" +
                "<button class='btn btn-success btn-xs' data-nodrag " +
                "ng-click='toggle(this)'>X</button>{{nitem.name}}" +
            "</div>" +
            "<ol ui-tree-nodes='' ng-model='nitem' ng-class='{hidden: collapsed}'>" +
                "<li ng-repeat='subitem in nitem.submenus track by $index' ui-tree-node jpnode item='{{subitem}}'></li>" +
            "</ol>"
        };

    }]);
