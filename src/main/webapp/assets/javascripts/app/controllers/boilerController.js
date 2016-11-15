angular.module("imap").controller("boilerController", function ($scope, $http, $location) {

  $scope.boilerId = $location.search().boilerId;
  $scope.controlObjectsInBoiler = [];

  getBoilerChecked();
  getBoilerInfo();

  function getBoilerChecked() {
    $http.get('/imap/boiler/'+ $scope.boilerId + "/check").then(function (response) {
      $scope.controlObjectsInBoiler = response.data;
    });
  }

  function getBoilerInfo() {
    $http.get('/imap/boiler/'+ $scope.boilerId).then(function (response) {
      $scope.boilerInfo = response.data;
    });
  }

  $scope.getStatusClass= function(paramStatusId) {
    if(paramStatusId == 1) {
      return "success";
    }
    else if(paramStatusId == 2) {
      return "warning";
    }
    else if(paramStatusId == 3) {
      return "danger";
    }
  };

  $scope.formatDate = function(date){
    var date = date.split("-").join("/");
    var dateOut = new Date(date);
    return dateOut;
  };

});