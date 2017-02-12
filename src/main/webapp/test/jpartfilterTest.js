describe('jpartfilter', function () {

    var _tags;
    var _filteredArticles;
    var _mockArticles = [];
    var _mockTags = [];
    var _jpartfilter;

    beforeEach(angular.mock.module('jakPoliczycApp'));

    beforeEach(angular.mock.inject(function (jpartfilter, mockTags, mockArticles) {
        _jpartfilter = jpartfilter;
        _mockTags = mockTags;
        _mockArticles = mockArticles;
    }));

    it('Nie przekazanie wartości filtrującej powinno spowodować wystąpieniem wyjątku', function () {
        expect(function () {
            _jpartfilter(_mockArticles, 'id', undefined);
        }).toThrowError('Nie podano jednego z parametrów');
    });

    it('Nie przekazanie nazwy atrybutu po którym wykonane jest filtrowanie powinno spowodować wystąpieniem wyjątku', function () {
        expect(function () {
            _jpartfilter(_mockArticles, undefined, 1);
        }).toThrowError('Nie podano jednego z parametrów');
    });

    it('Przekazanie wszystkich tagów powinno zwrócić zwrócić wszystkie artykuły', function () {
        // given
        _tags = [];

        // when
        _filteredArticles = _jpartfilter(_mockArticles, 'tags', _tags);

        // then
        expect(_filteredArticles.length).toBe(_mockArticles.length);
    });

    it('Przekazanie ograniczonej liczby tagów powinno zwrócić wyfiltrowane artykuły', function () {
        // given
        _tags = ['Funkcje', 'Algebra', 'Bryły'];

        // when
        _filteredArticles = _jpartfilter(_mockArticles, 'tags', _tags);

        // then
        expect(_filteredArticles.length).toBeLessThan(_mockArticles.length);
    });

    it('Przekazanie wartości filtrującej jako nietablicy powinno spowodować wystąpieniem wyjątku', function () {
        expect(function () {
            _jpartfilter(_mockArticles, 'id', 11);
        }).toThrowError('Wartość filtrująca musi być tablicą');
    });

    it('Przekazanie id submenu powinno wyfiltrować artykuły', function () {
        // given
        var menuId = 11;

        // when
        _filteredArticles = _jpartfilter(_mockArticles, 'menuId', [menuId]);

        // then
        expect(_filteredArticles.length).toBe(8);
    });

    it('Przekazanie pustej tablicy wartości filtrujących powinno zwrócić przekazaną kolekcję artykułów', function () {
        // given
        var articles = _mockArticles;

        // when
        _filteredArticles = _jpartfilter(_mockArticles, 'tags', []);

        // then
        expect(articles).toBe(_filteredArticles);
        expect(articles.length).toBe(_filteredArticles.length);
        expect(articles.length).toBeGreaterThan(0);
    });

});