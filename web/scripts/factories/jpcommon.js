angular.module('jakPoliczycFactories')
    .factory('jpcommon', [function() {

        var kind = function (letter) {
            if (letter === 'Z')
                return "ZADANIE";

            if (letter === 'T')
                return "TEORIA";

            throw new Error('Podano nieprawidłowy rodzaj artykułu. Dopuszczalne rodzaje to "Z" oraz "T"');
        };

        return {
            getKind: kind
        };

    }]);