angular.module('jakPoliczycDirectives')
    .directive('jpPagination', ['$document', 'jpPagingSorting', function($document, jpPagingSorting) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                pageSize: '@',
                getData: '&',
                success: '&',
                failure: '&'
            },
            link: function (scope, element, attrs) {

                var pageSize = attrs.pageSize || 10,
                    pageNo = 1,
                    first = false,
                    last = false,
                    threeDots = '...';

                $document.on('keydown', function (e) {
                    if (e.keyCode === 39 && !scope.isNextDisabled()) {
                        scope.next();
                    }
                    if (e.keyCode === 37 && !scope.isPreviousDisabled()) {
                        scope.previous();
                    }
                });

                scope.next = function () {
                    fireHttpRequest(pageNo + 1, pageSize);
                };

                scope.previous = function () {
                    fireHttpRequest(pageNo - 1, pageSize);
                };

                scope.isPreviousDisabled = function () {
                    return first;
                };

                scope.isNextDisabled = function () {
                    return last;
                };

                scope.isCellDisabled = function (value) {
                    return value === threeDots;
                };

                function generateArray(currentPage, totalPages) {
                    scope.cells = [];
                    if (totalPages === 1)
                        return;

                    var c = currentPage + 1;
                    if (c === 2) {
                        scope.cells.push(1);
                    }
                    if (c > 4) {
                        scope.cells.push(1);
                        scope.cells.push(threeDots);
                    }
                    if (c > 3 && c <= 4) {
                        scope.cells.push(1);
                        scope.cells.push(2);
                    }
                    if (c > 2 && c <= 3) {
                        scope.cells.push(1);
                    }
                    if (c > 2)
                        scope.cells.push(c - 1);

                    scope.cells.push(c);
                    if (c + 1 < totalPages) {
                        scope.cells.push(c + 1);
                    }
                    if (c + 3 < totalPages) {
                        scope.cells.push(threeDots);
                    }
                    if (c + 3 === totalPages) {
                        scope.cells.push(c + 2);
                    }
                    if (c < totalPages) {
                        scope.cells.push(totalPages);
                    }
                }

                scope.goToPage = function (cell) {
                    fireHttpRequest(cell - 1, pageSize);
                };

                scope.isCurrentPage = function (index) {
                    return index === pageNo + 1;
                };

                jpPagingSorting.setCallbacks(function httpSuccess(response) {
                    var data = response.data;
                    pageNo = data.number;
                    scope.totalPages = data.totalPages;
                    generateArray(pageNo, data.totalPages);
                    first = data.first;
                    last = data.last;
                    var i = 1;
                    angular.forEach(data.content, function (e) {
                        e.no = (data.number * data.size) + i++;
                    });
                    scope.success({'data' : data.content});
                }, function httpFailure(response) {
                    scope.failure({'response': response});
                });

                function fireHttpRequest(no, size) {
                    jpPagingSorting.fire({'pageNo': no, 'size': size}, scope.getData());
                }
                fireHttpRequest(0, pageSize);

            },
            templateUrl: 'views/templates/jp-pagination.html'
        }}]);