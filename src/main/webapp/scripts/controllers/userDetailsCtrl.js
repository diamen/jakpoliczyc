angular.module('jakPoliczycControllers')
    .controller('userDetailsCtrl', function ($scope, $cookieStore, jpauth) {

        var initUsername = function() {
            $scope.username = jpauth.getUsername();
        };
        initUsername();

        var initExpirationTime = function () {
            if (angular.isDefined(jpauth.getExpirationDate())) {
                $scope.expirationTime = jpauth.getExpirationDate().getTime();
            }
        };
        initExpirationTime();

        $scope.$on('login-down', function () {
            initUsername();
            initExpirationTime();
        });

        $scope.$on('logout-down', function () {
            $scope.expirationTime = undefined;
        });

        $scope.timerLogout = function () {
            $scope.$emit('logout-up');
            $scope.addAlert({'type': 'warning', 'msg': $scope.language.warnTimeout});
        };

        $scope.refreshToken = function () {
            jpauth.refreshToken().then(function success(response) {
                 $cookieStore.put("TOKEN", response.data.token);
                 initExpirationTime();
            });
        };

    });