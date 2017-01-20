describe('articlesCtrl && articleCtrl', function () {

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
        _articleController = $controller('articlesCtrl', {
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

     it('Próba odpytania funkcji o nieistniejący rodzaj artykułu powinno skutkować wyrzuceniem wyjątku', function () {
        // given
         var wrongLetter = 'L';

         // when-then
         expect(function () {
             _mockScope.getKind(wrongLetter);
         }).toThrowError('Podano nieprawidłowy rodzaj artykułu. Dopuszczalne rodzaje to "Z" oraz "T"');
     });

     it('Odpytanie przy użyciu odpowiedniego skrótu powinno zwrócić prawidłowy rodzaj artykułu', function () {
        // given
         var theoryLetter = 'T';
         var exerciseLetter = 'Z';

         // when
         var theoryResult = _mockScope.getKind(theoryLetter);
         var exerciseResult = _mockScope.getKind(exerciseLetter);

         // then
         expect(theoryResult === 'TEORIA' && exerciseResult === 'ZADANIE').toBeTruthy();
     });

});
