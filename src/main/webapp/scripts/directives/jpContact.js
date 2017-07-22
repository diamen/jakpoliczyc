angular.module('jakPoliczycDirectives')
    .directive('jpContact', ['$sanitize', 'broadcasterService', 'jpLabelService', 'jpNotifier', 'modalService',
        function($sanitize, broadcasterService, jpLabelService, jpNotifier, modalService) {

        var ctrl = function () {
            var vm = this;
            vm.sending = false;

            var clearForm = function () {
                vm.email = "";
                vm.title = "";
                vm.content = "";
                vm.contact.$setPristine();
            };

            vm.send = function (input) {
                vm.sending = true;
                broadcasterService.contact({'address': input.address, 'title': input.title, 'content': input.content})
                    .then(function success() {
                        clearForm();
                        vm.sending = false;
                        jpNotifier.notifySuccess('alertMailSend');
                    }, function error() {
                        jpNotifier.notifyError('alertMailErr');
                        vm.sending = false;
                });
            };

            vm.openModal = function (address, title, content) {
                modalService.execute(vm.send, jpLabelService.getLabel('msgMailSend'),
                    {'address': address, 'title': title, 'content': content});
            };
        };

        return {
            controller: ctrl,
            controllerAs: 'vm',
            bindToController: true,
            restrict: 'E',
            replace: true,
            scope: {},
            templateUrl: 'views/templates/jp-contact.html'
        }
    }]);