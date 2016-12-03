angular.module('jakPoliczycServices')
    .service("jpparser", [function() {

        return {
            parseTextBlock: function (input) {
                return input.split("<nl>");
            }
        }

    }]);