angular.module('jakPoliczycApp')

.factory('Loggg', function() {
    return function() {
        console.log('loggg');
    }
})

    .config(function($provide, executeProvider) {

        executeProvider.annotate($provide, {
            testService: [{
                jointPoint: 'before',
                advice: 'Loggg',
                methodPattern: /Lol/
            }]
        });

    })

    .service('testService', [function() {

        return {
            imLolService: function() {
                console.log('inside');
            }
        };

    }]);