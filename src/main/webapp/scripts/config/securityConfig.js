angular.module('jakPoliczycApp')
    .run(function ($rootScope, $state, jpauth, jpstorage) {

        $rootScope.$on('$stateChangeStart', function (event, toState) {

           if (!toState.isRedirect) {
               $state.get("login").isRedirect = false;
           }

           if (toState.name.indexOf("login") != -1 && !toState.isRedirect) {
               jpstorage.clear("PREVIOUS");
               return;
           }

           if (toState.data && toState.data.roles.length > 0) {
               if (jpauth.getRoles().length === 0) {
                   event.preventDefault();
                   jpstorage.put("PREVIOUS", toState.name);
                   var loginState = $state.get("login");
                   loginState.isRedirect = true;
                   $state.go(loginState);
                   return;
               }
               if (!jpauth.hasRole(toState.data.roles)) {
                   event.preventDefault();
                   $state.go("error", { no: 403 });
                   return;
               }
           }
        });

    });