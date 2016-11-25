var app = angular.module ('imap', ['ngRoute', 'ngResource', 'ngCookies']);

app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider){

  $httpProvider.interceptors.push('responseObserver');

  $routeProvider
      .when('/region',{
        templateUrl: 'assets/javascripts/app/views/region.html',
        controller: 'regionController'
      })
      .when('/town',{
        templateUrl: 'assets/javascripts/app/views/town.html',
        controller: 'townController'
      })
      .when('/boiler',{
        templateUrl: 'assets/javascripts/app/views/boiler.html',
        controller: 'boilerController'
      })
      .when('/login',{
        templateUrl: 'assets/javascripts/app/views/login.html',
        controller: 'loginController'
      })
      .otherwise(
          { redirectTo: '/region'}
      );
}]);

app.factory ('responseObserver', ['$rootScope', '$q', '$location', function ($rootScope, $q, $location) {

  return {
    'responseError': function(errorResponse) {
      switch (errorResponse.status) {
        case 403:
          if ($location.path() != "login") {
            $rootScope.targetUrl = "#" + $location.path();
          }
          $location.path("/login");
          break;
      }
      return $q.reject(errorResponse);
    }
  };
}]);