angular.module('jakPoliczycApp')

    .factory('jplogger', ['$log', function($log) {
        return function(args) {
            if(angular.isUndefined(args.args[0]))
                return;

            args.when === 'before' ?
                $log.debug(sprintf("Method '%s' called with arguments '%s'", args.method, args.args))
            :   $log.debug(sprintf("Method '%s' returned with arguments '%s'", args.method, args.result));
        }
    }])

    .config(function($provide, executeProvider) {

        executeProvider.annotate($provide, {
            jpvalidator: [
                {
                    jointPoint: executeProvider.BEFORE,
                    advice: 'jplogger',
                    methodPattern: /.*/
                },
                {
                    jointPoint: executeProvider.AFTER,
                    advice: 'jplogger',
                    methodPattern: /.*/
                }],
            jpparser: [
                {
                    jointPoint: executeProvider.BEFORE,
                    advice: 'jplogger',
                    methodPattern: /.*/
                },
                {
                    jointPoint: executeProvider.AFTER,
                    advice: 'jplogger',
                    methodPattern: /.*/
                }]
        });

    });