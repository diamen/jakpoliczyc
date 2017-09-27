angular.module('jakPoliczycControllers')
    .controller('linkCtrl', function ($scope, configurationService) {

        var facebook = 'FACEBOOK',
            youtube = 'YOUTUBE',
            keyProperty = 'keyy';

        configurationService.getConfigs().then(function success(response) {
            if (angular.isDefined(response.data) && Array.isArray(response.data)) {
                response.data.forEach(function (e) {
                    if (e[keyProperty] === facebook) {
                        $scope.facebookUrl = e.value;
                    }

                    if (e[keyProperty] === youtube) {
                        $scope.youtubeUrl = e.value;
                    }
                });
            }
        });

    });