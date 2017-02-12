angular.module('jakPoliczycFactories')
    .factory('jpcommon', ['$rootScope', function($rootScope) {

        var kind = function (letter) {
            if (letter === 'Z')
                return $rootScope.language.exercise.toUpperCase();

            if (letter === 'T')
                return $rootScope.language.theory.toUpperCase();

            throw new Error($rootScope.language.errKind);
        };

        return {
            getKind: kind
        };

    }]);