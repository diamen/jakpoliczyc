angular.module('jakPoliczycDirectives')
    .directive('jpClickOutside', ['$document', function($document) {
        return {
            restrict: 'A',
            scope: {},
            link: function(scope, element, attrs) {
                var exclude = document.querySelector(attrs.jpClickOutside);

                $document.on('click', function (event) {
                   if (element !== event.target && !element[0].contains(event.target)
                       && element.css('display') !== 'none' && event.target != exclude) {
                       element.css('display', 'none');
                   }

                });

            }
        }
    }]);