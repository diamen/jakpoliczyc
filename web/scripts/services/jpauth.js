angular.module('jakPoliczycServices')
    .service("jpauth", ['$http', function($http) {

        var login = function (username, password, callback) {
            $http({
                data: { user: username, pass: password },
                method: 'POST',
                url: '/login'
            }).then(function success(response) {
                callback(response.data);
            }, function error() {
                throw new Error("HTTP error");
            });
        };

        var logout = function (callback) {
            $http({
                method: 'POST',
                url: '/logout'
            }).then(function success(response) {
                callback(response.data);
            }, function error() {
                throw new Error("HTTP error");
            });
        };

        return {
            login: login,
            logout: logout
        };

    }]);