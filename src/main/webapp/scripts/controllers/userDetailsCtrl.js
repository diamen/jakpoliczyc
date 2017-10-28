angular.module('jakPoliczycControllers')
    .controller('userDetailsCtrl', function ($scope, $cookieStore, jpAuth) {

        var initUsername = function() {
            $scope.username = jpAuth.getUsername();
        };
        initUsername();

        var initExpirationTime = function () {
            if (angular.isDefined(jpAuth.getExpirationDate())) {
                $scope.expirationTime = jpAuth.getExpirationDate().getTime();
            }
        };
        initExpirationTime();

        $scope.$on('login-down', function () {
            initUsername();
            initExpirationTime();
        });

        $scope.$on('logout-down', function () {
            $scope.expirationTime = undefined;
            jpAuth.logout();
        });

        $scope.timerLogout = function () {
            $scope.$emit('logout-up');
            $scope.addAlert({'type': 'warning', 'msg': $scope.language.warnTimeout});
        };

        $scope.$on('refresh-token-down', function () {
            $scope.refreshToken();
        });

        $scope.refreshToken = function () {
            jpAuth.refreshToken().then(function success(response) {
                 $cookieStore.put("TOKEN", response.data.token);
                 initExpirationTime();
            });
        };

    });