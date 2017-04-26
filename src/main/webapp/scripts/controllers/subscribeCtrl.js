angular.module('jakPoliczycControllers')
    .controller('subscribeCtrl', function($scope, subscribeService, modalService) {

        $scope.subscribe = function (email) {
            subscribeService.insertOrRemoveSubscriber({'email' : email});
        };

        $scope.openModal = function (email) {
            modalService.execute($scope.subscribe, $scope.language.msgSubscribe, email);
        };

    });