angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function($scope, $http, $stateParams) {

        $scope.id = parseInt($stateParams.id, 10);

        $scope.$on('$stateChangeSuccess', function () {
            $scope.$emit('unselect-up');
        });

        ($scope.init = function (id) {
            $http({
                method: 'GET',
                url: '/article/id/' + id
            }).then(function success(response) {
                $scope.ready = true;
                $scope.article = response.data;
            }, function error() {
                throw new Error("HTTP error");
            });
        })($scope.id);

        $scope.addComment = function (author, content) {
            console.log(author + ' dodał komentarz o treści: ' + content);
        };

    });
