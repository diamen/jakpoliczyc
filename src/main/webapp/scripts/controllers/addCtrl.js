angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope, menuService, modalService) {

        $scope.pattern = {};
        $scope.pattern.tags = "[a-zA-Z]+";

        menuService.getMenus().then(function success(response) {
            $scope.menu = response.data;
            console.log($scope.menu);
        });

        $scope.saveToStorage = function () {
          console.log("TODO");
          // TODO
        };

        $scope.submit = function (args) {
          console.log(args);
          // TODO
        };

        $scope.openModalSaveToStorage = function () {
            modalService.execute($scope.saveToStorage, $scope.language.msgSaveSto);
        };

        $scope.openModalSubmit = function (args) {
            modalService.execute($scope.submit, $scope.language.msgPublish, args);
        };

    });