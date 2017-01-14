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
            modalService.execute($scope.saveToStorage, "Czy chcesz zapisać dotychczasowe pracę i dokończyć później? Uwaga! Wyniki nie zostaną opublikowane!");
        };

        $scope.openModalSubmit = function () {
            modalService.execute($scope.submit, "Czy chcesz opublikować artykuł?");
        };

    });