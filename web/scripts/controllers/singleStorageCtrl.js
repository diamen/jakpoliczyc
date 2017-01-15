angular.module('jakPoliczycControllers')
    .controller('singleStorageCtrl', function($scope, $rootScope, $http, $stateParams, modalService) {

        var id = $stateParams.id;

        $scope.getSingleStorage = function (id) {
            $http({
                method: 'GET',
                url: '/storage/' + id
            }).then(function success(response) {
                $scope.storage = response.data;
            }, function error() {
                throw new Error("HTTP error");
            });
        };

        $scope.getSingleStorage(id);

        $scope.saveToStorage = function () {
            console.log("TODO");
            // TODO
        };

        $scope.submit = function () {
            console.log("TODO");
            // TODO
        };

        $scope.openModalSaveToStorage = function () {
            modalService.execute($scope.saveToStorage, $rootScope.language.msgSaveSto);
        };

        $scope.openModalSubmit = function () {
            modalService.execute($scope.submit, $rootScope.language.msgPublish);
        };

    });
