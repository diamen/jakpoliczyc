angular.module('jakPoliczycServices')
    .service("jpartfilter", ['$rootScope', function($rootScope) {

        return function (articles, filterParamName, filterValue) {
            if (angular.isUndefined(filterValue) || angular.isUndefined(filterParamName) || angular.isUndefined(articles))
                throw new Error($rootScope.language.errParam);

            if (!Array.isArray(filterValue))
                throw new Error($rootScope.language.errFilter);

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