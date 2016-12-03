angular.module('jakPoliczycServices')
    .service("parser", [function() {

        return {
            parseTextBlock: function (input) {
                return input.split("<nl>");
            }
        }

    }]);