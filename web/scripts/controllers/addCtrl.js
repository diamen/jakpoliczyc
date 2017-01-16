angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope, modalService) {

        $scope.pattern = {};
        $scope.pattern.tags = "[a-zA-Z]+";

        $scope.saveToStorage = function () {
          console.log("TODO");
          // TODO
        };

        $scope.submit = function () {
          console.log("TODO");
          // TODO
        };

        $scope.openModalSaveToStorage = function () {
            modalService.execute($scope.saveToStorage, $scope.language.msgSaveSto);
        };

        $scope.openModalSubmit = function () {
            modalService.execute($scope.submit, $scope.language.msgPublish);
        };

    });