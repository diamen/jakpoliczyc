angular.module('jakPoliczycServices')
    .service("jpauth", ['$http', function($http) {

        var roles;

        var login = function (username, password, callback) {
            $http({
                data: { username: username, password: password },
                method: 'POST',
                url: '/login'
            }).then(function success(response) {
                callback(response.data);
                roles = [ 'ROLE_ADMIN' ]
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

        var getRoles = function () {
          return roles;
        };

        var hasRole = function(role) {
            return roles.indexOf(role) != -1;
        };

        return {
            getRoles: getRoles,
            hasRole: hasRole,
            login: login,
            logout: logout
        };

    }]);