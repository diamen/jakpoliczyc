angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function ($scope, $state, $timeout, $window, menuService, jpPagingSorting) {

        $scope.isAdmin = false;
        $scope.isMenuOpened = false;
        $scope.isTagsOpened = false;
        $scope.smallScreen = isSmallScreen();

        menuService.getMenus().then(function success(response) {
            $scope.menu = response.data;
        }, function error() {
            $scope.menu = [];
        });

        /* Events */
        $scope.$on('tags-up', function (event, args) {
            if (angular.isDefined(args)) {
                args.$name = 'tags';
            }
            passDown('filter-down', args);
        });

        $scope.$on('menu-up', function (event, args) {
            if (angular.isDefined(args)) {
                args.$name = 'menu';
            }
            passDown('filter-down', args);
        });

        $scope.$on('close-up', function () {
            $scope.isMenuOpened = false;
        });

        $scope.$on('unselect-up', function () {
            $scope.$broadcast('unselect-down');
        });

        $scope.$on('login-up', function () {
            $scope.$broadcast('login-down');
        });

        $scope.$on('logout-up', function () {
            $scope.$broadcast('logout-down');
        });

        /* States */
        $scope.goHome = function () {
            $timeout(function () {
                passDown('menu-down');
                passDown('tags-down');
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

        $scope.goLogin = function () {
            $state.go("login");
        };

        $scope.goStorage = function () {
            $state.go("articles.storage");
        };

        $scope.goBroadcast = function () {
            $state.go("broadcast");
        };

        $scope.goSingleStorage = function (id) {
            $state.go("articles.storage.id", {id: id});
        };

        $scope.redirect = function (url) {
            $window.open(url, '_blank');
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

        /* Alerts */
        $scope.alerts = [];
        $scope.alerts.SUCCESS = 'success';
        $scope.alerts.DANGER = 'danger';
        $scope.alerts.WARNING = 'warning';

        $scope.addAlert = function (alert) {
            $scope.alerts.push(alert);
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        /* RWD */
        $timeout(angular.element($window).bind('resize', function () {
            $scope.smallScreen = isSmallScreen();
        }), 100);

        function isSmallScreen() {
            return $window.innerWidth <= 768;
        }

        function passDown(eventName, args) {
            if (eventName === 'filter-down') {
                jpPagingSorting.clear();
            }

            if (!isHome($state.current.url)) {
                $scope.goHome();
            }

            $timeout(function () {
                $scope.$broadcast(eventName, args);
            }, 50);
        }

        function isHome(url) {
            return url === '/articles';
        }

    });
