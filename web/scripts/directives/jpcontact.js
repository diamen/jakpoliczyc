angular.module('jakPoliczycDirectives')
    .directive('jpcontact', ['$sanitize', function($sanitize) {

        var ctrl = function () {
            var vm = this;

            vm.send = function () {
              console.log('contact');
              // TODO
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