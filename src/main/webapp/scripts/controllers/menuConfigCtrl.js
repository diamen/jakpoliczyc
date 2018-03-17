angular.module('jakPoliczycControllers')
    .controller('menuConfigCtrl', function ($scope, menuService, modalService) {

        menuService.getMenuWithArticle().then(function success(response) {
            $scope.menuWithArticle = response.data;
        });

        var menuDrawer = function (menus, space) {
            menus.forEach(function (menu) {
                var submenus = menu.submenus;
                menuDrawer(submenus, space + "-");
            });
        };

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