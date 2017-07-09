angular.module('jakPoliczycControllers')
    .controller('storageCtrl', function($scope, $http, storageService) {

        $scope.storage = $scope.storage || [];

        $scope.getData = function () {
            return storageService.getStorages();
        };

        $scope.httpSuccess = function (data) {
            $scope.storage = data;
        };

        $scope.httpFailure = function (response) {
            console.log(response);
        };

        $scope.getColumns = function () {
            return [
                {
                    'name' : $scope.language.no,
                    'isSortable': false,
                    'css': 'col-md-1'
                },
                {
                    'name' : $scope.language.title,
                    'isSortable': true,
                    'attribute': 'title',
                    'css': 'col-md-4'
                },
                {
                    'name' : $scope.language.tags,
                    'isSortable': false,
                    'css': 'col-md-5'
                },
                {
                    'name' : $scope.language.date,
                    'isSortable': true,
                    'attribute': 'addedDate',
                    'css': 'col-md-2'
                }
            ];
        };

    });