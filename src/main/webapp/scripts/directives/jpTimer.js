angular.module('jakPoliczycDirectives')
    .directive('jpTimer', ['$interval', function($interval) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                maxTime: '@',
                stopFn: '&'
            },
            link: function(scope, element, attrs) {
                var isStarted;

                attrs.$observe('maxTime', function (value) {
                    if (angular.isDefined(value) && value.length > 0) {
                        var now = new Date();
                        scope.start(new Date(parseInt(value)));
                    } else {
                        scope.stop();
                    }
                });

                scope.start = function (time) {
                    if (isStarted) {
                        scope.stop();
                    }
                    isStarted = $interval(function () {
                        var now = new Date();
                        scope.remainTime = time.getTime() - now.getTime();

                        if (scope.remainTime <= 0) {
                            scope.stopFn()();
                            scope.stop();
                        }
                    }, 30);
                };

                scope.stop = function () {
                  if (isStarted) {
                      $interval.cancel(isStarted);
                      isStarted = undefined;
                  }
                };

                scope.$on('$destroy', function () {
                    scope.stop();
                    scope = {};
                });
            },
            templateUrl: 'views/templates/jp-timer.html'
        }
    }]);