angular.module('jakPoliczycApp')
    .run(function ($rootScope, $state, jpauth) {

        $rootScope.$on('$stateChangeStart', function (event, toState) {
           if (toState.data && toState.data.role) {
               if (!jpauth.getRoles()) {
                   event.preventDefault();
                   $state.go('login');
                   return;
               }
               if (!jpauth.hasRole(toState.data.role)) {
                   event.preventDefault();
                   $state.go("error", { no: 403 });
                   return;
               }
           }
        });

    });