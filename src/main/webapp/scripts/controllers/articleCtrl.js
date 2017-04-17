angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function($scope, $http, $stateParams, $window, articleService, jpPdfService) {

        var editables = [];
        $scope.id = parseInt($stateParams.id, 10);
        $scope.isEditable = false;

        $scope.$on('$stateChangeSuccess', function () {
            $scope.$emit('unselect-up');
        });

        $scope.getSingle = function (id) {
            articleService.getSingle(id).then(
                function success(response) {
                    $scope.ready = true;
                    $scope.article = response.data;
                    $scope.article.author = 'anonim';
                    $scope.ncontent = $scope.article.story.content;
                })
        };
        $scope.getSingle($scope.id);

        $scope.getPdf = function () {
            var intro = angular.element(document.querySelector("#intro"))[0];
            var content = angular.element(document.querySelector("#content"))[0];
            console.log(content);
            jpPdfService.saveAsPdf(content);
        };

        $scope.submit = function () {
            // TODO
        };

        $scope.edit = function () {
            $scope.isEditable = true;

            if (editables.length === 0) {
                angular.forEach(document.querySelectorAll('[contenteditable="false"'), function (raw) {
                    editables.push(angular.element(raw));
                });
            }
            toggleEditable(true);
        };

        $scope.cancel = function () {
            $window.location.reload();
        };

        $scope.remove = function () {
            // TODO
        };

        function toggleEditable(state) {
            angular.forEach(editables, function (editable) {
                editable.attr('contenteditable', state);
            });
        }

        $scope.addComment = function (author, content) {
            articleService.postComment($scope.id, author, content);
        };

        $scope.deleteComment = function (commentId) {
            articleService.deleteComment($scope.id, commentId);
        };

    });
