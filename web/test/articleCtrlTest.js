describe('articleCtrl', function () {

    var _mockScope = {};
    var _rootScope = {};
    var _mockArticles = [];
    var _mockTags = [];
    var _articleController;

    beforeEach(angular.mock.module('jakPoliczycApp'));

    beforeEach(angular.mock.inject(function ($controller, $rootScope) {
        _mockScope = $rootScope.$new();
        _rootScope = $rootScope;
        _articleController = $controller('articleCtrl', {
            $scope: _mockScope
        });
    }));

     beforeEach(angular.mock.inject(function (mockTags, mockArticles) {
         _mockTags = mockTags;
         _mockArticles = mockArticles;
     }));

     it('Rzucony event powinien wywołać funkcję filtrowania', function () {
         spyOn(_mockScope, 'filter');
        _rootScope.$broadcast('tags-down', { tags: _mockTags });

        expect(_mockScope.filter).toHaveBeenCalled();
     });

     describe('Testy funkcji filtrującej artykuły', function () {

         var _tags;
         var _filteredArticles;

         beforeEach(function () {
             _mockScope.articles = _mockArticles;
         });

         it('Przekazanie wszystkich tagów powinno zwrócić zwrócić wszystkie artykuły', function () {
            // given
             _tags = [];

            // when
             _filteredArticles = _mockScope.filter(_mockArticles, _tags);

             // then
             expect(_filteredArticles.length).toBe(_mockArticles.length);
         });

         it('Przekazanie ograniczonej liczby tagów powinno zwrócić wyfiltrowane artykuły', function () {
            // given
             _tags = ['Funkcje', 'Algebra', 'Bryły'];

             // when
             _filteredArticles = _mockScope.filter(_mockArticles, _tags);

             // then
             expect(_filteredArticles.length).toBeLessThan(_mockArticles.length);
         });

     });

});
