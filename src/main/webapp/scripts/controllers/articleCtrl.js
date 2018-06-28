angular.module('jakPoliczycControllers')
    .controller('articleCtrl', function ($scope, $http, $sce, $stateParams, $window, articleService, jpStorage, modalService) {

        var editables = [];
        $scope.id = parseInt($stateParams.id, 10);
        $scope.isEditable = false;
        $scope.prepareYoutubeUrl = articleService.prepareYoutubeUrl;

        $scope.$on('$stateChangeSuccess', function () {
            $scope.$emit('unselect-up');
        });

        $scope.getSingle = function (id) {
            articleService.getSingle(id).then(
                function success(response) {
                    $scope.ready = true;
                    $scope.article = response.data;
                    $scope.article.ncontent = $scope.article.story.content;
                    if (angular.isDefined($scope.article.tags)) {
                        $scope.article.tags = $scope.article.tags.map(function (e) {
                            return e.name;
                        }).join(' ');
                    }
                    if (angular.isDefined($scope.article.url)) {
                        $scope.url = $sce.trustAsResourceUrl($scope.article.url);
                    }
                })
        };
        $scope.getSingle($scope.id);

        $scope.submit = function () {
            jpStorage.clear('menus');
            $scope.$broadcast('publish-down');
            var tags = $scope.article.tags;
            if (tags && angular.isString(tags) && tags.length > 0) {
                tags = articleService.prepareTags(tags);
            } else {
                tags = [];
            }
            var request = {
                story: {
                    title: $scope.article.story.title,
                    intro: $scope.article.story.intro,
                    content: $scope.article.ncontent
                },
                menus: jpStorage.retrieve('menus') || $scope.article.menu,
                tags: tags,
                youtube: $scope.article.url,
                pdf: $scope.article.pdf,
                kahoot: $scope.article.kahoot
            };
            modalService.execute(function (data) {
                articleService.updateArticle(data.id, data.request).then(function success() {
                    $scope.goHome();
                    $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertUptArtSuc});
                }, function error() {
                    $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertUptArtErr});
                });
            }, $scope.language.msgArtUpt, {'id': $scope.id, 'request': request});
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
            modalService.execute(function () {
                $window.location.reload();
            }, $scope.language.msgUpdCancel);
        };

        $scope.delete = function () {
            modalService.execute(function (id) {
                articleService.deleteArticle(id).then(function success() {
                    $scope.goHome();
                    $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertRemArtSuc});
                }, function error() {
                    $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertRemArtSuc});
                });
            }, $scope.language.msgArtRem, $scope.id);
        };

        function toggleEditable(state) {
            angular.forEach(editables, function (editable) {
                editable.attr('contenteditable', state);
            });
        }

        $scope.addComment = function (author, content) {
            articleService.postComment($scope.id, author, content).then(function success() {
                $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertComAdd});
                $scope.article.comments.push({'author': author, 'content': content, 'addedDate': new Date()});
            }, function error() {
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertComErr});
            });
        };

        $scope.deleteComment = function (commentId) {
            articleService.deleteComment($scope.id, commentId).then(function success() {
                $scope.article.comments = $scope.article.comments.filter(function(comment) {
                   return comment.id !== commentId;
                });
                $scope.addAlert({'type': $scope.alerts.SUCCESS, 'msg': $scope.language.alertComDel});
            }, function error() {
                $scope.addAlert({'type': $scope.alerts.DANGER, 'msg': $scope.language.alertComDelErr});
            });
        };

    });
