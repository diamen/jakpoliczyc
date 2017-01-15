angular.module('jakPoliczycControllers')
    .controller('subscribeCtrl', function($scope, modalService) {

        $scope.subscribe = function () {
          // TODO
        };

        $scope.openModal = function () {
            modalService.execute($scope.subscribe, $scope.language.msgSubscribe);
        };

    });