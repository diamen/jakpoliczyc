angular.module('jakPoliczycDirectives')
    .filter('jpStopwatch', function () {
        return function (input) {

            var format = function (input) {
                if (input < 10) {
                    input = '0' + input;
                }
                return input;
            };

            if (input) {
                var hours = parseInt(input/3600000, 10);
                input %= 3600000;
                var minutes = parseInt(input/60000, 10);
                input %= 60000;
                var secs = parseInt(input/1000, 10);

                return format(hours) + ':' + format(minutes) + ':' + format(secs);
            }

        };
    });