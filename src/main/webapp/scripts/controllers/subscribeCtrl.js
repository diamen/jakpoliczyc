angular.module('jakPoliczycControllers')
    .controller('subscribeCtrl', function($scope, subscribeService, modalService) {

        $scope.subscribe = function (email) {
            subscribeService.insertOrRemoveSubscriber({'email' : email}).then(function success(response) {
                $scope.email = '';

                if (response.status === 200) {
                    $scope.addAlert({'type': 'warning', 'msg': $scope.language.alertMailExist});
                }

                if (response.status === 201) {
                    $scope.addAlert({'type': 'success', 'msg': $scope.language.alertMailAdd});
                }
            });
        };

        $scope.openModal = function (email) {
            modalService.execute($scope.subscribe, $scope.language.msgSubscribe, email);
        };

    });