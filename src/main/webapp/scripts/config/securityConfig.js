angular.module('jakPoliczycApp')
    .run(function ($rootScope, $state, jpAuth, jpStorage) {

        $rootScope.$on('$stateChangeStart', function (event, toState) {

           if (!toState.isRedirect) {
               $state.get("login").isRedirect = false;
           }

           if (toState.name.indexOf("login") != -1 && !toState.isRedirect) {
               jpStorage.clear("PREVIOUS");
               return;
           }

           if (toState.data && toState.data.roles.length > 0) {
               if (jpAuth.getRoles().length === 0) {
                   event.preventDefault();
                   jpStorage.put("PREVIOUS", toState.name);
                   var loginState = $state.get("login");
                   loginState.isRedirect = true;
                   $state.go(loginState);
                   return;
               }
               if (!jpAuth.hasRoles(toState.data.roles)) {
                   event.preventDefault();
                   $state.go("error", { no: 403 });
               }
           }
        });

    });