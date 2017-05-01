angular.module('jakPoliczycControllers')
    .controller('singleStorageCtrl', function($scope, $http, $stateParams, $window, articleService, modalService, storageService) {

        var editables = [];
        var id = $stateParams.id;
        $scope.isEditable = false;

        storageService.getStorage(id).then(function success(response) {
            $scope.single = response.data;
            if (angular.isDefined($scope.single.stags)) {
                $scope.single.stags = $scope.single.stags.map(function(e) { return e.name; }).join(' ');
            }
        });

        $scope.submit = function () {
            var stags = $scope.single.stags;
            if (stags && angular.isString(stags) && stags.length > 0) {
                stags = articleService.prepareTags(stags);
            } else {
                stags = [];
            }
            var request = {
                story: {
                    title: $scope.single.story.title,
                    intro: $scope.single.story.intro,
                    content: $scope.single.story.content
                },
                stags: stags
            };
            modalService.execute(function(data) {
                storageService.updateStorage(data.id, data.request).then(function success() {
                    $scope.addAlert({'type': 'success', 'msg': $scope.language.alertStoUpd});
                    $scope.goHome();
                });
            }, $scope.language.msgArtUpt, {'id': id, 'request': request});
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
            modalService.execute(function() { $window.location.reload(); }, $scope.language.msgUpdCancel);
        };

        $scope.delete = function () {
            modalService.execute(function(id) {
                storageService.deleteStorage(id).then(function success() {
                    $scope.addAlert({'type': 'success', 'msg': $scope.language.alertStoRem});
                    $scope.goHome();
                });
            }, $scope.language.msgArtRem, id);
        };

        function toggleEditable(state) {
            angular.forEach(editables, function (editable) {
                editable.attr('contenteditable', state);
            });
        }

    });
