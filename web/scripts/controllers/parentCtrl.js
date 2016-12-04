angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function($scope, mockMenu) {

        $scope.mockMenu = mockMenu;
        $scope.$on('tags-up', function (event, args) {
            $scope.$broadcast('tags-down', args);
        });

    });
