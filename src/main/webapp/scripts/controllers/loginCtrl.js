angular.module('jakPoliczycControllers')
    .controller('loginCtrl', function ($scope, $state, $cookieStore, jwtHelper, jpAuth, jpStorage) {
        $scope.authError = false;

        $scope.login = function (username, password) {
            jpAuth.login(username, password).then(function success(response) {
                $cookieStore.put("TOKEN", response.data.token);
                var toState = jpStorage.retrieve("PREVIOUS");
                toState ? $state.go(toState) : $state.go("articles");
                $scope.$emit("login-up");
                $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': 'Zostałeś zalogowany'});
            }, function error() {
                $scope.authError = true;
                $scope.form.$setPristine();
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': 'Nie udało się zalogować'});
            });
        };

    });