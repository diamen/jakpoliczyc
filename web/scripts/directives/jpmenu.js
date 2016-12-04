angular.module('jakPoliczycDirectives')
    .directive('jpmenu', ['$compile', 'mockMenu', function ($compile, mockMenu) {
        return {
            restrict: 'E',
            scope: {
                elements: '@'
            },
            link: function(scope, element, attrs) {

                var mainHtml = '<div id="tree-root" class="jpmenu" ui-tree>' +
                                '<ol ui-tree-nodes ng-model="mockMenu"></ol>' +
                                '</div>';
                element.append( $compile(mainHtml)(scope) );

                scope.test = function () {
                    console.log('toggle');
                };

                var menuScanner = function (item, elem) {
                    var elem = angular.isDefined(elem) ? elem : element.children().children();
                    var liHtml = "<li ui-tree-node><div class='node' ui-tree-handle>" +
                        "<button class='btn btn-success btn-xs' data-nodrag " +
                        "ng-click='test()'>X</button>" + item.name + "</div></li>";
                    var liElem = angular.element(liHtml);
                    elem = $compile(elem.append(liElem))(scope);

                    console.log('li: ' + item.name);

                    if (angular.isDefined(item.submenus)) {
                        var olElem = angular.element("<ol ng-class='{hidden: collapsed}' ui-tree-nodes='' ng-model='item.submenus'></ol>");
                        liElem.append(olElem);
                        console.log('ol');
                        angular.forEach(item.submenus, function (submenu) {
                            menuScanner(submenu, olElem);
                        });
                    }

                };

                angular.forEach(mockMenu, function (menu) {
                    menuScanner(menu);
                });

            }
        };

    }]);
