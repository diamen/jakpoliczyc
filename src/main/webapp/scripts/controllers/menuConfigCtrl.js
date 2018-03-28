angular.module('jakPoliczycControllers')
    .controller('menuConfigCtrl', function ($scope, menuService, modalService) {

        menuService.getMenuWithArticle().then(function success(response) {
            $scope.menuWithArticle = response.data;
        });

        $scope.$on('redraw-down', function (event, args) {
            $scope.menuWithArticle = args;
        });

        $scope.done = function () {
            modalService.execute(function () {
                console.log($scope.menuWithArticle);
                menuService.postMenu($scope.menuWithArticle).then(function success() {
                    $scope.goHome();
                    $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertUptMenSuc});
                }, function error() {
                    $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertUptMenErr});
                });
            }, $scope.language.msgSaveMenu);
        }

    });