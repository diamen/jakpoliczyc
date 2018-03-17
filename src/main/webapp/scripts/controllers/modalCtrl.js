angular.module('jakPoliczycControllers')
    .controller('modalCtrl', function ($scope, $uibModalInstance, msg, input) {
        $scope.msg = msg;
        $scope.input = input;

        $scope.ok = function () {
            $uibModalInstance.close($scope.input);
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    });