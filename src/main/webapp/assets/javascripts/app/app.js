var app = angular.module ('imap', ['ngRoute','ngResource']);

app.config(function($routeProvider){
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
      .otherwise(
          { redirectTo: '/region'}
      );
});