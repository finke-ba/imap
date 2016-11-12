angular.module("imap").controller("regionController", function ($scope, $http, $location) {
	$scope.circles = [];
	$scope.towns = [];

	updateTask();

	function updateTask() {
		getRegionChecked().then(function (response) {
			response.forEach(function (item) {
				$scope.towns[item.townId] = item;
				getTown(item.townId).then(function (response) {
					$scope.towns[response.townId]["coordX"] = response.coordX;
					$scope.towns[response.townId]["coordY"] = response.coordY;
					$scope.towns[response.townId]["coordXName"] = response.coordXName;
					$scope.towns[response.townId]["coordYName"] = response.coordYName;
					$scope.towns[response.townId]["townName"] = response.townName;
					drawTown($scope.towns[response.townId]);
				});
			});
		});
	}

	function getRegionChecked() {
		return $http.get('/imap/region/check').then(function (response) {
			var towns = [];
			for (var i = 0; i < response.data.length; i++) {
				var town = response.data[i];
				towns[town.townId] = {
					paramStatus: town.paramStatus,
					paramStatusId: town.paramStatusId,
					townId: town.townId
				};
			}
			return towns;
		});
	}

	function getTown(townId) {
		return $http.get('/imap/town/' + townId).then(function (response) {
			return {
				coordX: response.data.coordX,
				coordY: response.data.coordY,
				coordXName: response.data.coordXName,
				coordYName: response.data.coordYName,
				townName: response.data.townName,
				townId: response.data.townId
			};
		})
	}

	function drawTown(town) {
		var fillColor;
		if (town.paramStatusId == 1) {
			fillColor = "green";
		} else if (town.paramStatusId == 2) {
			fillColor = "yellow";
		} else if (town.paramStatusId == 3) {
			fillColor = "red";
		}
		drawCircle(town.townId, town.coordX, town.coordY, 10, town.townName, town.coordXName, town.coordYName, fillColor);
	}

	function drawCircle(townId, coordX, coordY, radius, townName, coordXName, coordYName, fillColor) {
		draw(coordX, coordY, townName, coordXName, coordYName, fillColor);
		$scope.circles[townId] = new Circle(coordX, coordY, radius, townId);
	}

	function draw(x, y, townName, textX, textY, fillColor) {
		var canvas = document.getElementById('canvas');
		var context = canvas.getContext('2d');
		context.beginPath();
		context.arc(x, y, 10, 0, 2 * Math.PI, false);
		context.fillStyle = fillColor;
		context.fill();
		context.stroke();
		context.fillStyle = "#000000";
		context.font = "15pt Arial";
		context.fillText(townName, textX, textY);
	}

	function Circle(x, y, radius, townId) {
		this.left = parseInt(x) - parseInt(radius);
		this.top = parseInt(y) - parseInt(radius);
		this.right = parseInt(x) + parseInt(radius);
		this.bottom = parseInt(y) + parseInt(radius);
		this.townId = townId;
	}

	$scope.clickOnCanvas = function (event) {
		var position = event.target.getBoundingClientRect();

		var clickedX = event.pageX - position.left;
		var clickedY = event.pageY - position.top;

		for (var i = 1; i < $scope.towns.length; i++) {
			if (clickedX < $scope.circles[i].right && clickedX > $scope.circles[i].left &&
				clickedY > $scope.circles[i].top && clickedY < $scope.circles[i].bottom) {
				$location.path("/town").search('townId=' + $scope.towns[i].townId);
			}
		}
	};

});