angular.module('jakPoliczycServices')
    .service("jpAuth", ['$http', '$cookieStore', 'jwtHelper', function ($http, $cookieStore, jwtHelper) {

        var _roles = [];

        var login = function (username, password) {
            return $http({
                data: {username: username, password: password},
                method: 'POST',
                url: '/login'
            });
        };

        var logout = function (callback) {
            _roles = [];
            $cookieStore.remove("TOKEN");
            if (callback) {
                callback();
            }
        };

        var refreshToken = function () {
            return $http({
                method: 'GET',
                url: '/refresh'
            });
        };

        var initRoles = function () {
            var token = $cookieStore.get("TOKEN");
            var auth = jwtHelper.decodeToken(token).authorities;
            if (auth.indexOf(",") != -1) {
                angular.copy(auth.split(","), _roles);
            } else {
                _roles.push(auth);
            }
        };

        var getRoles = function () {
            if (_roles.length === 0 && $cookieStore.get("TOKEN")) {
                initRoles();
            }
            return _roles;
        };

        var hasRoles = function (roles) {
            if (_roles.length === 0 && $cookieStore.get("TOKEN")) {
                initRoles();
            }
            return _.intersection(_roles, roles).length == roles.length;
        };

        var getUsername = function () {
            if ($cookieStore.get("TOKEN")) {
                return jwtHelper.decodeToken($cookieStore.get("TOKEN")).sub;
            }
        };

        var getExpirationDate = function () {
            if ($cookieStore.get("TOKEN")) {
                return jwtHelper.getTokenExpirationDate($cookieStore.get("TOKEN"));
            }
        };

        var isLogin = function () {
            if ($cookieStore.get("TOKEN")) {
                if (jwtHelper.decodeToken($cookieStore.get("TOKEN")).sub) {
                    return true;
                }
            }
            return false;
        };

        return {
            getExpirationDate: getExpirationDate,
            getUsername: getUsername,
            getRoles: getRoles,
            hasRoles: hasRoles,
            isLogin: isLogin,
            login: login,
            logout: logout,
            refreshToken: refreshToken
        };

    }]);