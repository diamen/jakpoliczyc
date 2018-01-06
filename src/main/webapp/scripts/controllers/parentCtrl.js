angular.module('jakPoliczycControllers')
    .controller('parentCtrl', function ($scope, $rootScope, $controller, $timeout, $state, $document, $window, jpAuth, jpPagingSorting, menuService) {

        $scope.isAdmin = false;
        $scope.isMenuOpened = false;
        $scope.isTagsOpened = false;
        $scope.smallScreen = isSmallScreen();

        $controller('stateCtrl', {$scope: $scope});

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
            $scope.passDown('filter-down', args);
        });

        $scope.$on('menu-up', function (event, args) {
            if (angular.isDefined(args)) {
                args.$name = 'menu';
            }
            $scope.passDown('filter-down', args);
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
        $rootScope.alerts = [];
        $scope.alerts.SUCCESS = 'success';
        $scope.alerts.DANGER = 'danger';
        $scope.alerts.WARNING = 'warning';

        $rootScope.addAlert = function (alert) {
            $scope.alerts.push(alert);
        };

        $rootScope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        /* RWD */
        $timeout(angular.element($window).bind('resize', function () {
            $scope.smallScreen = isSmallScreen();
        }), 100);

        function isSmallScreen() {
            return $window.innerWidth <= 768;
        }

        $scope.passDown = function(eventName, args) {
            if (eventName === 'filter-down') {
                jpPagingSorting.clear();
            }

            if (!isHome($state.current.url)) {
                $scope.goHome();
            }

            $timeout(function () {
                $scope.$broadcast(eventName, args);
            }, 50);
        };

        function isHome(url) {
            return url === '/articles';
        }

    });
