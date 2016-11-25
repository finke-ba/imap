angular.module ("imap").controller ("loginController", [
  '$scope',
  '$rootScope',
  '$location',
  '$window',
  '$timeout',
  'authService',
  function ($scope, $rootScope, $location, $window, $timeout, authService) {

    $scope.username   = '';
    $scope.password   = '';
    $scope.remember   = false;
    $scope.loginError = false;
    $rootScope.isLogin = false;

    $scope.login = function () {
      authService.authenticate (
          $scope.username,
          $scope.password,
          $scope.remember,
          function (success) {
            if (success) {
              $rootScope.isLogin = true;

              $location.path ($rootScope.targetUrl ? $rootScope.targetUrl : "/region");
            } else {
              $scope.loginError = true;
            }
          });
    };

    $scope.resetStatus = function () {
      $scope.loginError = false;
    };

    $scope.logout = function () {
      authService.logout ().success (function () {
        $rootScope.isLogin = false;
        $window.location.reload();
      });
    };

    $scope.showLogout = function () {
      return $rootScope.isLogin;
    };

  }
]);
