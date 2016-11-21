describe("tagCtrl", function () {

    var mockScope = {};
    var controller;

    beforeEach(angular.mock.module('jakPoliczycControllers'));

    beforeEach(angular.mock.inject(function ($controller, $rootScope) {
        mockScope = $rootScope.$new();
        controller = $controller("tagCtrl", {
            $scope: mockScope
        });
    }));

    it('Zmiana wartości logicznej na przeciwną', function () {
       // given
        var index = 5;

        // when
        mockScope.tick(5);

        //then
        expect(mockScope.selectedTags[index]).toBeTruthy();
    });

    it('Zamienienie wszystkich wartości logicznych na fałsz', function () {
       // given
        mockScope.selectedTags[0] = true;
        mockScope.selectedTags[1] = true;
        mockScope.selectedTags[2] = true;

        // when
        mockScope.untickAll();

        // then
        expect(mockScope.selectedTags[0] && mockScope.selectedTags[1] && mockScope.selectedTags[2]).toBeFalsy();
    });

});