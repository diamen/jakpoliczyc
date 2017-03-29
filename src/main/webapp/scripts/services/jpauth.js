angular.module('jakPoliczycServices')
    .service("jpauth", ['$http', '$cookieStore', 'jwtHelper', function($http, $cookieStore, jwtHelper) {

        var _roles = [];

        var login = function (username, password) {
            return $http({
                data: { username: username, password: password },
                method: 'POST',
                url: '/login'
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
            var token = $cookieStore.get("TOKEN");
            if (token) {
                var auth = jwtHelper.decodeToken(token).authorities;
                if (auth.indexOf(",") != -1) {
                    angular.copy(auth.split(","), _roles);
                } else {
                    _roles.push(auth);
                }
            }
            return _roles;
        };

        var hasRole = function(roles) {
            return _.intersection(_roles, roles).length == roles.length;
        };

        return {
            getRoles: getRoles,
            hasRole: hasRole,
            login: login,
            logout: logout
        };

    }]);