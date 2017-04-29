angular.module('jakPoliczycDirectives')
    .directive('jpcontact', ['$sanitize', 'broadcasterService', function($sanitize, broadcasterService) {

        var ctrl = function () {
            var vm = this;

            vm.send = function (address, title, content) {
                broadcasterService.contact({'address': address, 'title': title, 'content': content});
            };
        };

        return {
            controller: ctrl,
            controllerAs: 'vm',
            bindToController: true,
            restrict: 'E',
            replace: true,
            scope: {},
            templateUrl: 'views/templates/jpcontact.html'
        }
    }]);