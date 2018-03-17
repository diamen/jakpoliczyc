angular.module('jakPoliczycServices')
    .service("modalService", ['$uibModal', function ($uibModal) {

        return {
            execute: function (fn, msg, args) {

                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/views/templates/modal.html',
                    controller: 'modalCtrl',
                    size: 'sm',
                    resolve: {
                        msg: function () {
                            return msg;
                        },
                        input: function () {

                        }
                    }
                });

                modalInstance.result.then(function () {
                    fn(args);
                }, function () {
                    return;
                });
            },
            inputExecute: function (fn, msg, inputTxt, args) {

                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/views/templates/modal-input.html',
                    controller: 'modalCtrl',
                    size: 'm',
                    resolve: {
                        msg: function () {
                            return msg;
                        },
                        input: function () {
                            return inputTxt;
                        }
                    }
                });

                modalInstance.result.then(function (input) {
                    fn(input, args);
                }, function () {
                    return;
                });
            }
        };

    }]);