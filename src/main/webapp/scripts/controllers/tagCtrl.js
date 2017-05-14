angular.module('jakPoliczycControllers')
    .controller('tagCtrl', function($scope, $http, $timeout, $sce, $window, tagService) {

        var _selected;
        var _searcher = new Searcher();

        $scope.tags = [];
        $scope.selectedTags = [];

        tagService.getTags().then(function success(response) {
            if (angular.isDefined(response.data)) {
                $scope.tags = response.data;
                fireEvent($scope.tags, $scope.selectedTags);
            }
        });

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

        $scope.unfocus = function () {
            if(_searcher.doesSelectedTagExist(_selected)) {
                $scope.selectedTags[_searcher.getIndex()] = true;
                $scope.ngModelOptionsSelected('');
            }

            $timeout(function () {
                if (!$scope.focusUL) { $scope.focus = false; }
            }, 500);
        };

        $scope.tick = function(index, id) {
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

        $scope.$on('menu-down', function () {
            $scope.selectedTags = Array($scope.tags.length).fill(false);;
        });

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