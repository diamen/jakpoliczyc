angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope, articleService, menuService, modalService) {

        menuService.getMenus().then(function success(response) {
            $scope.menu = response.data;
            console.log($scope.menu);
        });

        $scope.saveToStorage = function () {
          console.log("TODO");
          // TODO
        };

        $scope.submit = function (args) {
            articleService.postArticle(args);
        };

        $scope.openModalSaveToStorage = function () {
            modalService.execute($scope.saveToStorage, $scope.language.msgSaveSto);
        };

        $scope.openModalSubmit = function (args) {
            modalService.execute($scope.submit, $scope.language.msgPublish, args);
        };

    });