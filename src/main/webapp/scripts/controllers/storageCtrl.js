angular.module('jakPoliczycControllers')
    .controller('storageCtrl', function($scope, $http, storageService) {

        $scope.storage = $scope.storage || [];

        $scope.getData = function () {
            return storageService.getStorages();
        };

        $scope.httpSuccess = function (data) {
            $scope.storage = data;
        };

        $scope.httpFailure = function (response) {
            console.log(response);
        };

    });