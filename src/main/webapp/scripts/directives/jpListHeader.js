angular.module('jakPoliczycDirectives')
    .directive('jpListHeader', ['jpPagingSorting', function(jpPagingSorting) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                columns: '@'
            },
            link: function (scope, element, attrs) {

                scope.cols = angular.fromJson(attrs.columns).map(function (e) {
                    e.reverse = false;
                    e.selected = false;
                    return e;
                });

                scope.sort = function (index, attribute) {
                    scope.cols[index].reverse = !scope.cols[index].reverse;
                    scope.cols[index].selected = true;
                    for (var i = 0; i < scope.cols.length; i++) {
                        if (i !== index) {
                            scope.cols[i].selected = false;
                            scope.cols[i].reverse = false;
                        } else {
                            var sortAttribute = scope.cols[i].reverse ? attribute + ',desc' : attribute + ',asc';
                        }
                    }

                    jpPagingSorting.fire({'sort': sortAttribute});
                };

                scope.getStyle = function (index) {
                    return scope.cols[index].isSortable ? {'cursor': 'pointer'} : '';
                }

            },
            templateUrl: 'views/templates/jp-list-header.html'
        }}]);