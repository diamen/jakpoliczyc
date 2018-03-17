angular.module('jakPoliczycServices')
    .service("jpTemplateService", [function () {

        return function (placeholder) {
            return function () {
                var args = arguments;
                return placeholder.replace(/{(\d+)}/g, function (match, number) {
                    return typeof args[number] != 'undefined' ? args[number] : match;
                });
            }
        };

    }]);
