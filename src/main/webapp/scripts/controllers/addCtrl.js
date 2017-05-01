angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope, articleService, menuService, modalService, storageService) {

        menuService.getMenus().then(function success(response) {
            $scope.menu = response.data;
        });

        $scope.saveToStorage = function (args) {
            storageService.postStorage(args).then(function success(response) {
                if (response.status === 201) {
                    $scope.addAlert({'type': 'success', 'msg': $scope.language.alertStoAdd});
                    $scope.goStorage();
                }
            }, function error() {
                $scope.addAlert({'type': 'error', 'msg': $scope.language.alertStoErr});
            });
        };

        $scope.submit = function (args) {
            articleService.postArticle(args);
        };

        $scope.openModalSaveToStorage = function (title, intro, content, stags) {
            var request = {
                'story': {
                    'title': title,
                    'intro': intro,
                    'content': content
                },
                'stags': articleService.prepareTags(stags)
            };
            modalService.execute($scope.saveToStorage, $scope.language.msgSaveSto, request);
        };

        $scope.openModalSubmit = function (args) {
            modalService.execute($scope.submit, $scope.language.msgPublish, args);
        };

    });