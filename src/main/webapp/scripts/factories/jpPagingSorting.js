angular.module('jakPoliczycFactories')
    .factory("jpPagingSorting", [function() {

        var data,
            pageNo,
            size,
            sort,
            success,
            failure,
            subscribers = [];

        function getPaginationUrl(url, pageNo, size, sort) {
            var sortUrlPart = angular.isDefined(sort) ? '&sort=' + sort : '';
            return url + "?page=" + pageNo + '&size=' + size + sortUrlPart;
        }

        function subscribe(clearFn) {
            subscribers.push(clearFn);
        }

        function clear() {
            sort = undefined;
            angular.forEach(subscribers, function (clearFn) {
                clearFn();
            });
        }

        return {
            setCallbacks: function (s, f) {
                success = s;
                failure = f;
            },
            clear: clear,
            subscribe: subscribe,
            fire: function(input, d) {
                data = d || data;
                pageNo = angular.isDefined(input.pageNo) ?  input.pageNo : pageNo;
                size = angular.isDefined(input.size) ? input.size : size;
                sort = angular.isDefined(input.sort) ? input.sort : sort;
                var config = data.getConfig();
                config.url = getPaginationUrl(config.url, pageNo, size, sort);
                data.fire(config).then(function(response) {
                    success(response);
                }, function(response) {
                    failure(response);
                });
            }
        }

    }]);