angular.module('jakPoliczycDirectives')
    .directive('postarea', [function () {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: '/views/templates/jppostarea.html',
            link: function($scope) {

                $scope.preview = function() {
                    $scope.show = true;

                    if($scope.show)
                        $scope.invokeParse($scope.expression);
                };

            }
        };

    }]);