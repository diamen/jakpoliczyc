angular.module('jakPoliczycServices')
    .service("subscribeService", ['$http', function($http) {

        return {
            insertOrRemoveSubscriber: function(data) {
                return $http({
                    method: 'POST',
                    url: '/subscriber',
                    headers: {'Content-Type': 'application/json'},
                    data: data
                });
            }
        }

    }]);