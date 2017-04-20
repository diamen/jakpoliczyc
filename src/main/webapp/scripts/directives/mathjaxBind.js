angular.module('jakPoliczycDirectives')
    .directive("mathjaxBind", ['$compile', function ($compile) {
        return {
            priority: -1,
            restrict: "A",
            link: function ($scope, $element, $attrs) {
                var parser = new DOMParser();
                var shouldProcess = $attrs.process || true;
                var temp = $element.clone({widthDataAndEvents: true, deepWithDataAndEvents: true});

                $attrs.$observe('process', function (val) {
                    shouldProcess = (val === "true");
                    shouldProcess ? $scope.invokeParse($attrs.content) : $scope.invokeUnparse();
                });

                $attrs.$observe('content', function (val) {
                    if (val && val.length > 0) {
                        if (shouldProcess) {
                            $scope.invokeParse(val);
                        }
                    }
                });

                $scope.invokeParse = function (input) {
                    input = input.replace(/\[latex]/, "<latex>").replace(/\[\/latex]/, "<\/latex>").replace(/\[photo]/, "<photo>").replace(/\[\/photo]/, "<\/photo>");
                    var htmlDoc = parser.parseFromString(input, "text/html");
                    var latexTags = htmlDoc.getElementsByTagName("latex");
                    var photoTags = htmlDoc.getElementsByTagName("photo");

                    for (var i = 0; i < latexTags.length; i++) {
                        var latexExpression = angular.element("<script type='math/tex'>").html(latexTags[i].innerHTML ? latexTags[i].innerHTML : "")[0];
                        input = input.replace(/<latex>(.*?)<\/latex>/, latexExpression.outerHTML);
                    }

                    for (i = 0; i < photoTags.length; i++) {
                        var img = angular.element('<img>');
                        img.attr('src', photoTags[i].innerHTML);
                        img.css('max-width', '100%');
                        input = input.replace(/<photo>(.*?)<\/photo>/, img[0].outerHTML);
                    }

                    $element.html("");
                    $element.append(input);
                    MathJax.Hub.Queue(["Reprocess", MathJax.Hub, $element[0]]);
                };

                $scope.invokeUnparse = function () {
                    temp.attr('contenteditable', true);
                    temp.removeAttr('mathjax-bind');
                    temp.removeAttr('content');
                    var toReplace = $compile(temp[0].outerHTML)($scope);
                    $element.replaceWith(toReplace);
                }
            }
        };
    }]);