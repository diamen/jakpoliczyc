angular.module('jakPoliczycControllers')
    .controller('kahootCtrl', function ($scope, kahootService) {

        kahootService.getKahoots().then(function success(response) {
            if (response.data) {
                $scope.kahoots = kahootService.addTranslationOfDifficulties(response.data);
            }
        });

    });
