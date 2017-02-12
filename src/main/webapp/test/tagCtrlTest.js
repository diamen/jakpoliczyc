describe("tagCtrl", function () {

    var _mockScope = {};
    var _controller;
    var _mockTags = [];

    beforeEach(angular.mock.module('jakPoliczycApp'));

    beforeEach(angular.mock.inject(function ($controller, $rootScope) {
        _mockScope = $rootScope.$new();
        _controller = $controller("tagCtrl", {
            $scope: _mockScope
        });
    }));

    beforeEach(angular.mock.inject(function (mockTags) {
        _mockTags = mockTags;
    }));

    it('Zmiana wartości logicznej na przeciwną', function () {
       // given
        var index = 5;

        // when
        _mockScope.tick(5);

        //then
        expect(_mockScope.selectedTags[index]).toBeTruthy();
    });

    it('Zamienienie wszystkich wartości logicznych na fałsz', function () {
       // given
        _mockScope.selectedTags[0] = true;
        _mockScope.selectedTags[1] = true;
        _mockScope.selectedTags[2] = true;

        // when
        _mockScope.untickAll();

        // then
        expect(_mockScope.selectedTags[0] && _mockScope.selectedTags[1] && _mockScope.selectedTags[2]).toBeFalsy();
    });

    describe('Weryfikacja przekazania wartości do osobnego kontrolera', function () {

        beforeEach(function () {
           spyOn(_mockScope, '$emit');
        });

        it('Zaznaczenie lub odznaczenie tagu powinno skutkować przekazaniem wartości do osobnego kontrolera', function () {
            // given
            var index = _mockTags.length - 1;

            // when
            _mockScope.tick(index);

            // then
            expect(_mockScope.$emit).toHaveBeenCalled();
        });

        it('Odznaczenie wszystkich tagów powinno skutkować przekazaniem wartości do osobnego kontrolera', function () {
            // when
            _mockScope.untickAll();

            // then
            expect(_mockScope.$emit).toHaveBeenCalled();
        });

    });

});