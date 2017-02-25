angular.module('jakPoliczycDirectives')
    .directive('jpadd', ['jpstorage', function(jpstorage) {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                scope.add = {};
                scope.menu = angular.fromJson(attrs.items);

                attrs.$observe('storage', function (val) {
                    init(val);
                });

                scope.submit = function () {
                    jpstorage.clear('menus');
                    scope.$broadcast('publish-down');
                };

                scope.$on('publish-up', function (event, args) {
                    var noOfMenusElem = element.find('jpcategory').length + element.find('jpcategoryInput').length;
                    var noOfMenus = jpstorage.retrieve('menus').length;

                    if (noOfMenus === noOfMenusElem) {
                        scope.add.menus = jpstorage.retrieve('menus');
                        scope.openModalSubmit(scope.add);
                    }
                });

                function init(storage) {
                    if (angular.isDefined(storage) && storage.length > 0) {
                        var add = angular.fromJson(storage);
                        add.tags = add.tags.join(" ");
                        scope.add = add;
                    }
                }
            },
            templateUrl: 'views/templates/jpadd.html'
        }
    }]);