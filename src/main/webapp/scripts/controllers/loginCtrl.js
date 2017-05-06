angular.module('jakPoliczycControllers')
    .controller('loginCtrl', function ($scope, $state, $cookieStore, jwtHelper, jpauth, jpstorage) {
        $scope.authError = false;

        $scope.login = function (username, password) {
            jpauth.login(username, password).then(function success(response) {
                $cookieStore.put("TOKEN", response.data.token);
                var toState = jpstorage.retrieve("PREVIOUS");
                toState ? $state.go(toState) : $state.go("articles");
                $scope.$emit("login-up");
            }, function error() {
                $scope.authError = true;
                $scope.form.$setPristine();

            });
        };

    });