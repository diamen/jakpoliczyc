angular.module('jakPoliczycApp')
    .run(function ($rootScope) {

        (function languageConfig() {
            $rootScope.language = {};

            Object.getOwnPropertyNames(PL).map(function (prop) {
               $rootScope.language[prop] = PL.hasOwnProperty(prop) ? PL[prop] : undefined;
            });
        })();

    });