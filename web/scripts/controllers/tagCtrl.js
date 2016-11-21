angular.module('jakPoliczycControllers')
    .controller('tagCtrl', function($scope, $timeout, $sce, $window) {

        var _selected;
        var _expander;
        var _searcher;
        var _topMover;

        $scope.animation = {};
        $scope.animation.expanded = false;
        $scope.animation.expandedText = $sce.trustAsHtml('<div>Zwiń listę tagów</div>');
        $scope.animation.unexpandedText = $sce.trustAsHtml('<div>Rozwiń listę tagów</div>');

        $scope.tags = [ 'Funkcje', 'Algebra', 'Bryły', 'Analiza', 'Geometria', 'Analityka', 'Trygonometria',
            'Prawdopodobieństwo', 'Kombinatoryka', 'Topologia', 'Stereometria', 'Wielomiany', 'Logarytmy',
            'Całki', 'Pochodne', 'Tales', 'Pitagoras', 'Równania' ];

        $timeout(function init() {
            _topMover = angular.element(document.querySelector('ul.tagger'));
            moveTop(_topMover);
            _expander = angular.element(document.querySelector('#expander'));
            _searcher = new searcher();
        }, 0);

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
        };

        ($scope.untickAll = function() {
            $scope.selectedTags = Array($scope.tags.length).fill(false);
        })();

        function moveTop(element) {
            var height = element.prop('offsetHeight');
            element.css('top', '-' + height + 'px');
        }

        function isExpanded() {
            return $scope.animation.expanded;
        }

        function searcher() {
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