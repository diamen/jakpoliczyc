angular.module('jakPoliczycControllers')
    .controller('broadcasterCtrl', function($scope, broadcasterService, modalService, jpNotifier) {

        $scope.sending = false;

        var clearForm = function () {
            $scope.title = '';
            $scope.content = '';
            $scope.broadForm.$setPristine();
        };

        $scope.broadcast = function (title, content) {
            modalService.execute(function (data) {
                $scope.sending = true;
                broadcasterService.broadcast(data).then(function success() {
                    $scope.sending = false;
                    jpNotifier.notifySuccess('alertBroSuc');
                    clearForm();
                }, function error() {
                    $scope.sending = false;
                    jpNotifier.notifyError('alertBroErr');
                });
            }, $scope.language.msgBroadcast, {'title': title, 'content' : content});
        };

    });