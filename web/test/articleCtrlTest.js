describe('articleCtrl', function () {

    var _mockScope = {};
    var _rootScope = {};
    var _mockTags = [];
    var _articleController;
    var _jpartfilter;

    beforeEach(angular.mock.module('jakPoliczycApp'));

    beforeEach(angular.mock.inject(function ($controller, $rootScope) {
        _jpartfilter = jasmine.createSpy('jpartfilter', function () {
            /* fake */
        });

        _mockScope = $rootScope.$new();
        _rootScope = $rootScope;
        _articleController = $controller('articleCtrl', {
            $scope: _mockScope,
            jpartfilter: _jpartfilter
        });
    }));

     beforeEach(angular.mock.inject(function (mockTags) {
         _mockTags = mockTags;
     }));

     it('Rzucony event powinien wywołać funkcję filtrowania', function () {
         // when
        _rootScope.$broadcast('tags-down', { tags: _mockTags });

        // then
        expect(_jpartfilter).toHaveBeenCalled();
     });

});
