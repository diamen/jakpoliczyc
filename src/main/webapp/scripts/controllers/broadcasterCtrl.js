angular.module('jakPoliczycControllers')
    .controller('broadcasterCtrl', function($scope, broadcasterService, modalService) {

        $scope.broadcast = function (title, content) {
            modalService.execute(function (data) {
                broadcasterService.broadcast(data);
            }, $scope.language.msgBroadcast, {'title': title, 'content' : content});
        };

    });