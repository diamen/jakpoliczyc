angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function($scope) {

        $scope.$on('tags-up', function (event, args) {
            $scope.$broadcast('tags-down', args);
        });

    });
