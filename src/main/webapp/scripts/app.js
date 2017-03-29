angular.module('jakPoliczycApp',
    [
        'ngMockE2E',
        'ngCookies',
        'ngSanitize',
        'angular-jwt',

        'ui.bootstrap',
        'ui.router',
        'ui.tree',

        'jakPoliczycControllers',
        'jakPoliczycDirectives',
        'jakPoliczycFactories',
        'jakPoliczycServices',
        'jakPoliczycRouters'
    ]);