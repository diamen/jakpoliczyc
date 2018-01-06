angular.module('jakPoliczycControllers')
    .controller('tagCtrl', function($scope, $http, $timeout, $sce, $window, tagService, articleService) {

        var _selected;
        var _searcher = new Searcher();

        var eventTargetName = "tags";

        $scope.opened = false;
        $scope.tags = [];
        $scope.selectedTags = [];

        tagService.getTags().then(function success(response) {
            if (angular.isDefined(response.data)) {
                $scope.tags = response.data;
                fireEvent($scope.tags, $scope.selectedTags);
            }
        });

        $scope.close = function() {
            $scope.opened = false;
        };

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
            var tagIds = tags.filter(function (value, index) {
                return selectedTags[index];
            }).map(function (elem) {
                return elem.id;
            });

            $scope.$emit('tags-up', tagIds.length ? articleService.getArticlesByTagId(tagIds) : undefined);
        }

        $scope.$on('filter-down', function (event, args) {
            if (args && args.$name === eventTargetName) {
                return;
            }
            $scope.selectedTags = Array($scope.tags.length).fill(false);
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