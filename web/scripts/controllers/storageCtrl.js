angular.module('jakPoliczycControllers')
    .controller('storageCtrl', function($scope, $rootScope, $http, jpcommon) {

        $scope.storage = $scope.storage || [];

        $http({
            cache: true,
            method: 'GET',
            url: '/storage'
        }).then(function success(response) {
            $scope.storage = response.data;
        }, function error() {
            throw new Error("HTTP error");
        });

        $scope.getKind = jpcommon.getKind;

    });