angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope, articleService, menuService, modalService, storageService) {

        menuService.getMenus().then(function success(response) {
            $scope.menu = response.data;
        }, function error() {
            $scope.menu = [];
        });

        $scope.saveToStorage = function (args) {
            storageService.postStorage(args).then(function success(response) {
                if (response.status === 201) {
                    $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertStoAdd});
                    $scope.goStorage();
                }
            }, function error() {
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertStoErr});
            });
        };

        $scope.submit = function (args) {
            articleService.postArticle(args).then(function success() {
                $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertArtAdd});
                $scope.goHome();
            }, function error() {
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertArtErr});
            });
        };

        $scope.openModalSaveToStorage = function (title, intro, content, stags, url, pdf, kahoot) {
            var request = {
                'story': {
                    'title': title,
                    'intro': intro,
                    'content': content
                },
                'url': url,
                'pdf': pdf,
                'kahoot': kahoot,
                'stags': articleService.prepareTags(stags)
            };
            modalService.execute($scope.saveToStorage, $scope.language.msgSaveSto, request);
        };

        $scope.openModalSubmit = function (args) {
            modalService.execute($scope.submit, $scope.language.msgPublish, args);
        };

    });