angular.module('jakPoliczycDirectives')
    .directive('jpadd', ['jpstorage', 'articleService', function(jpstorage, articleService) {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                scope.add = {};

                attrs.$observe('storage', function (val) {
                    init(val);
                });

                scope.submit = function () {
                    jpstorage.clear('menus');
                    scope.$broadcast('publish-down');
                };

                scope.$on('publish-up', function () {
                    var noOfMenusElem = element.find('jpcategory').length + element.find('jpcategoryInput').length;
                    var noOfMenus = jpstorage.retrieve('menus').length;

                    if (noOfMenus === noOfMenusElem) {
                        if (scope.add.tags && angular.isString(scope.add.tags) && (scope.add.tags.length > 0)) {
                            var tags = articleService.prepareTags(scope.add.tags);
                        }
                        var request = {
                            story: {
                                title: scope.add.title,
                                intro: scope.add.intro,
                                content: scope.add.content
                            },
                            menus: jpstorage.retrieve('menus'),
                            tags: tags
                        };
                        scope.openModalSubmit(request);
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