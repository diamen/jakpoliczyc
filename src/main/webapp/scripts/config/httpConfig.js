angular.module('jakPoliczycApp')
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('jptokeninterceptor');
    }]);