angular.module('jakPoliczycServices')
    .service("jpartfilter", [function() {

        return function (articles, filterParamName, filterValue) {
            if (angular.isUndefined(filterValue) || angular.isUndefined(filterParamName) || angular.isUndefined(articles))
                throw new Error('Nie podano jednego z parametrów');

            if (!Array.isArray(filterValue))
                throw new Error('Wartość filtrująca musi być tablicą');

            filteredArticles = [];

            if (filterValue.length === 0)
                return articles;

            articles.forEach(function (article) {
                _.intersection(Array.isArray(article[filterParamName]) ?
                    article[filterParamName] : [article[filterParamName]], filterValue).length > 0 ? filteredArticles.push(article) : '';
            });

            return filteredArticles;
        };

    }]);