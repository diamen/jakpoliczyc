angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function($scope, $http, $stateParams) {

        $scope.id = parseInt($stateParams.id, 10);

        $scope.$on('$stateChangeSuccess', function () {
            $scope.$emit('unselect-up');
        });

        ($scope.init = function (id) {
            $http({
                method: 'GET',
                url: '/articles/' + id
            }).then(function success(response) {
                $scope.ready = true;
                $scope.article = response.data;
                console.log($scope.article);
            }, function error() {
                throw new Error("HTTP error");
            });
        })($scope.id);

        $scope.addComment = function (author, content) {
            // TODO
            console.log(author + ' dodał komentarz o treści: ' + content);
        };

    });
