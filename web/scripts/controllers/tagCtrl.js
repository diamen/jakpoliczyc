angular.module('jakPoliczycControllers')
    .controller('tagCtrl', function($scope, $http, $timeout, $sce, $window, testService) {

        var _selected;
        var _expander;
        var _searcher = new Searcher();
        var _topMover;

        $scope.animation = {};
        $scope.animation.expanded = false;
        $scope.animation.expandedText = $sce.trustAsHtml('<div>Zwiń listę tagów</div>');
        $scope.animation.unexpandedText = $sce.trustAsHtml('<div>Rozwiń listę tagów</div>');
        $scope.tags = [];
        $scope.selectedTags = [];

        $http({
            method: 'GET',
            url: '/tags'
        }).then(function success(response) {
            $scope.tags = response.data;
            fireEvent($scope.tags, $scope.selectedTags);
        }, function error() {
            throw new Error("HTTP error");
        });

        $scope.render = function() {
            $timeout(function () {
                _topMover = angular.element(document.querySelector('ul.tagger'));
                moveTop(_topMover);
                _expander = angular.element(document.querySelector('#expander'));
            }, 0);
        };

        $timeout(angular.element($window).bind('resize', function() {
           moveTop(_topMover);
           $scope.animation.expanded = false;
           $scope.$apply();
        }), 50);

        $scope.ngModelOptionsSelected = function(value) {
            if (arguments.length) {
                _selected = value;
            } else {
                return _selected;
            }
        };

        $scope.modelOptions = {
            debounce: {
                default: 500,
                blur: 250
            },
            getterSetter: true
        };

        $scope.focus = function () {
            testService.imLolService();

            if(!isExpanded())
                _expander.triggerHandler('click');
        };

        $scope.unfocus = function () {
            if(_searcher.doesSelectedTagExist(_selected)) {
                $scope.selectedTags[_searcher.getIndex()] = true;
                $scope.ngModelOptionsSelected('');
            }

            if(isExpanded()) {
                $timeout(function() {
                    index = $scope.selectedTags.indexOf(true);

                    if (index === -1)
                        _expander.triggerHandler('click');
                }, 500);
            }
        };

        $scope.tick = function(index) {
            $scope.selectedTags[index] = !$scope.selectedTags[index];
            fireEvent($scope.tags, $scope.selectedTags);
        };

        ($scope.untickAll = function() {
            $scope.selectedTags = Array($scope.tags.length).fill(false);
            fireEvent($scope.tags, $scope.selectedTags);
        })();

        function fireEvent(tags, selectedTags) {
            $scope.$emit('tags-up', tags.filter(function (value, index) {
                return selectedTags[index];
            }));
        }

        function moveTop(element) {
            var height = element.prop('offsetHeight');
            element.css('top', '-' + height + 'px');
        }

        function isExpanded() {
            return $scope.animation.expanded;
        }

        function Searcher() {
            var index = -1;

            var does = function (tag) {
                index = $scope.tags.indexOf(tag);
                return index !== -1;
            };

            var get = function () {
                return index;
            };

            return {
                doesSelectedTagExist : does,
                getIndex : get
            };
        }

    });