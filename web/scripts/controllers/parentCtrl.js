angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function($scope, $state, $timeout, $window, mockMenu) {

        $scope.isMenuOpened = false;
        $scope.isTagsOpened = false;
        $scope.mockMenu = mockMenu;
        $scope.smallScreen = isSmallScreen();

        $scope.$on('tags-up', function (event, args) {
            $scope.$broadcast('tags-down', args);
        });

        $scope.$on('menu-up', function (event, args) {
            $scope.$broadcast('menu-down', args);
        });

        $scope.$on('close-up', function () {
           $scope.isMenuOpened = false;
           $scope.isTagsOpened = false;
        });

        /* States */
        $scope.goHome = function () {
            $state.go("articles");
        };

        $scope.goArticle = function (id) {
            $state.go("articles.id", { id: id });
        };

        $scope.openMenu = function () {
            $scope.isMenuOpened = true;
        };

        $scope.openTags = function () {
            $scope.isTagsOpened = true;
        };

        $scope.clear = function () {
            $scope.$broadcast('menu-down');
            $scope.$broadcast('tags-down');
            $scope.$broadcast('unselect-down');
        };

        /* RWD */
        $timeout(angular.element($window).bind('resize', function() {
            $scope.smallScreen = isSmallScreen();
        }), 100);

        function isSmallScreen() {
            return $window.innerWidth <= 768;
        }

    });
