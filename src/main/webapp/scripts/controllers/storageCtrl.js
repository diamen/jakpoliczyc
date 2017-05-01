angular.module('jakPoliczycControllers')
    .controller('storageCtrl', function($scope, $http, storageService) {

        $scope.storage = $scope.storage || [];

        storageService.getStorages().then(function success(response) {
            $scope.storage = response.data;
        });

    });