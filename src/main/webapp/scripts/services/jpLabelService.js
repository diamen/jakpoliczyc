angular.module('jakPoliczycServices')
    .service("jpLabelService", ['$rootScope', function($rootScope) {

        var getLabel = function (key) {
            return $rootScope.language[key];
        };

        return {
            getLabel: getLabel
        };

    }]);
