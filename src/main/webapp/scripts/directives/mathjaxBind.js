angular.module('jakPoliczycDirectives')
    .directive("mathjaxBind", function() {
        return {
            restrict: "A",
            controller: ["$scope", "$element",
                function($scope, $element) {

                    var parser = new DOMParser();

                    $scope.invokeParse = function(input) {
                        var htmlDoc = parser.parseFromString(input, "text/html");
                        var latexTags = htmlDoc.getElementsByTagName("latex");
                        var photoTags = htmlDoc.getElementsByTagName("photo");

                        for (var i = 0; i< latexTags.length; i++) {
                            var latexExpression = angular.element("<script type='math/tex'>").html(latexTags[i].innerHTML ? latexTags[i].innerHTML :  "")[0];
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

                }]
        };
    });