angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function($scope, $state, $timeout, $window, mockMenu) {

        $scope.mockMenu = mockMenu;
        $scope.smallScreen = false;

        $scope.$on('tags-up', function (event, args) {
            $scope.$broadcast('tags-down', args);
        });

        $scope.$on('menu-up', function (event, args) {
            $scope.$broadcast('menu-down', args);
        });

        /* States */
        $scope.goHome = function () {
            $state.go("articles");
        };

        $scope.goArticle = function (id) {
            $state.go("articles.id", { id: id });
        };

        /* RWD */
        $timeout(angular.element($window).bind('resize', function() {
            $scope.smallScreen = ($window.innerWidth <= 768);
        }), 100);

    });
