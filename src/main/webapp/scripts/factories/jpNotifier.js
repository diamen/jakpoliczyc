angular.module('jakPoliczycFactories')
    .factory('jpNotifier', ['$rootScope', function($rootScope) {

        var level = {};
        level.SUCCESS = 'success';
        level.DANGER = 'danger';
        level.WARNING = 'warning';

        var notifySuccess = function (msgLabel) {
            notify(level.SUCCESS, msgLabel);
        };

        var notifyError = function (msgLabel) {
            notify(level.DANGER, msgLabel);
        };

        var notifyWarning = function (msgLabel) {
            notify(level.WARNING, msgLabel);
        };

        var notify = function (level, msgLabel) {
            var alert = {'type': level, 'msg': $rootScope.language[msgLabel]};
            $rootScope.alerts.push(alert);
        };

        return {
            notifySuccess: notifySuccess,
            notifyError: notifyError,
            notifyWarning: notifyWarning
        };

    }]);