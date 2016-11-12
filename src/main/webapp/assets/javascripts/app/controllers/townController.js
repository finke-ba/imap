angular.module("imap").controller("townController", function ($scope, $http, $location) {

	//temp js from old view
/*

	function init() {
		var url = window.location.origin;
		var townId = getUrlParameter('townId');

		$.ajax({
			url: url + "/imap/town/" + townId
		}).then(function (data) {
			var townName = data.townName;
			$('.breadcrumb li:nth-child(2)').text(townName);
			$('.panel-title').text("Котельные в городе " + townName);
		});

		$.ajax({
			url: url + "/imap/town/" + townId + "/check"
		}).then(function (data) {
			var trHTML = '';
			$.each(data, function (i, item) {
				trHTML += getTr(item.paramStatusId) + "<td class='hidden'>" +
					item.boilerId + '</td><td>' +
					item.boilerName + '</td><td>' +
					item.boilerAddress + '</td><td>' +
					item.paramStatus + '</td></tr>';
			});
			$('#towns_table').append(trHTML);
			clickRowTableEvent();
		});

	}

	function getTr(paramStatusId) {
		if (paramStatusId == 1) {
			return "<tr class='click-row success' data-href='boiler.html?boilerId='>";
		} else if (paramStatusId == 2) {
			return "<tr class='warning'>";
		} else if (paramStatusId == 3) {
			return "<tr class='click-row danger' data-href='boiler.html?boilerId='>";
		}
	}

	function clickRowTableEvent() {
		$(".click-row").click(function () {
			window.document.location = $(this).data("href") + $(this).find("td:first").html();
		});
	}

	function getUrlParameter(sParam) {
		var sPageURL = window.location.search.substring(1);
		var sURLVariables = sPageURL.split('&');
		for (var i = 0; i < sURLVariables.length; i++) {
			var sParameterName = sURLVariables[i].split('=');
			if (sParameterName[0] == sParam) {
				return sParameterName[1];
			}
		}
	}



*/
});

