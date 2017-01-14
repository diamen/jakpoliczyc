angular.module('jakPoliczycControllers')
    .controller('addCtrl', function($scope) {

        $scope.pattern = {};
        $scope.pattern.tags = "[a-zA-Z]+";
        
    });