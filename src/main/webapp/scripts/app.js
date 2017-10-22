angular.module('jakPoliczycApp',
    [
        'ngMockE2E',
        'ngCookies',
        'ngSanitize',
        'angular-jwt',
        'angularTrix',

        'ui.bootstrap',
        'ui.router',
        'ui.tree',

        'jakPoliczycConstants',
        'jakPoliczycControllers',
        'jakPoliczycDirectives',
        'jakPoliczycFactories',
        'jakPoliczycServices',
        'jakPoliczycRouters'
    ]);