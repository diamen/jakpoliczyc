angular.module('jakPoliczycControllers')
    .controller('stateCtrl', function ($scope, $state, $timeout) {

        /* States */
        $scope.goHome = function () {
            $timeout(function () {
                $scope.passDown('menu-down');
                $scope.passDown('tags-down');
                $scope.$broadcast('unselect-down');
            }, 0);

            $state.go("articles");
        };

        $scope.goAdd = function () {
            $state.go("articles.add");
        };

        $scope.goArticle = function (id) {
            $state.go("articles.id", {id: id});
        };

        $scope.goKahoot = function () {
            $state.go("kahoot");
        };

        $scope.goLogin = function () {
            $state.go("login");
        };

        $scope.goStorage = function () {
            $state.go("articles.storage");
        };

        $scope.goBroadcast = function () {
            $state.go("broadcast");
        };

        $scope.goConfig = function () {
            $state.go("config");
        };

        $scope.goKahootConfig = function () {
            $state.go("kahoot-config");
        };

        $scope.goSingleStorage = function (id) {
            $state.go("articles.storage.id", {id: id});
        };

    });
