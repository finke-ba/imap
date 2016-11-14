angular.module("imap").controller("townController", function ($scope, $http, $location) {

	$scope.townId = $location.search().townId;
	$scope.boilersInTown = [];

	getTownChecked();

	function getTownChecked() {
		$http.get('/imap/town/'+ $scope.townId + "/check").then(function (response) {
			$scope.boilersInTown = response.data;
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

	$scope.clickOnBoiler = function(boiler) {
		if (boiler.paramStatusId == 1 || boiler.paramStatusId == 2) {
			$location.path("/boiler").search('boilerId=' + boiler.boilerId);
		}
	};

});

