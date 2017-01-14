angular.module('jakPoliczycServices')
    .service("jpvalidator", [function() {

        return {
            notUrls: function(value) {
                return angular.isDefined(value) ?
                    (value.includes("www.") || value.includes("http://") || value.includes("https://")) : false;
            }
        }

    }]);