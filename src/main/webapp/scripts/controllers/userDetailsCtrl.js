angular.module('jakPoliczycControllers')
    .controller('userDetailsCtrl', function ($scope, $cookieStore, jpauth) {

        var initUsername = function() {
            $scope.username = jpauth.getUsername();
        };
        initUsername();

        $scope.$on('login-down', function () {
            initUsername();
        });

    });