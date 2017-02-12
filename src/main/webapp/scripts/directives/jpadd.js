angular.module('jakPoliczycDirectives')
    .directive('jpadd', [function() {
        return {
            restrict: 'E',
            scope: true,
            link: function(scope, element, attrs) {
                attrs.$observe('storage', function (val) {
                    init(val);
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