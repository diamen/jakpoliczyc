angular.module('jakPoliczycServices')
    .service("modalService", ['$uibModal', function($uibModal) {

        return {
            execute: function(fn, msg, args) {

                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/views/templates/modal.html',
                    controller: 'modalCtrl',
                    size: 'sm',
                    resolve: {
                        msg: function () {
                            return msg;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    fn(args);
                }, function () {
                    return;
                });
            }
        };

    }]);