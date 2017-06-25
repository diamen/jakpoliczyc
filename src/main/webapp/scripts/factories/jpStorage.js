angular.module('jakPoliczycFactories')
    .factory('jpStorage', [function() {

        var container = {};

        var put = function(key, value) {
            container[key] = value;
        };

        var retrieve = function(key) {
            return container[key];
        };

        var clear = function (key) {
            if (container.hasOwnProperty(key)) {
                delete container[key];
            }
        };

        return {
            clear: clear,
            put: put,
            retrieve: retrieve
        };

    }]);