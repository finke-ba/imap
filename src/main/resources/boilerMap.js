$(document).ready(function draw() {
	var img = $('#imgmap');
	var canvas = $('#cnvs');
	//setInterval('updateTask()', 3000)
	canvas.css({position: 'absolute', left: 0, top: 0, padding: 0, border: 0});
	//добавляем div
	// делаем ему фоновой картинкой нашу карту, ее ширину и высоту передаем блоку
	// добавляем этот блок в наш уже существующий

	wrap = $('<div>').css({
		display: 'block',
		background: 'url(' + img.attr('src') + ')',
		position: 'relative',
		padding: 0,
		width: img.width(),
		height: img.height()
	}).attr('id', 'canvas_div').appendTo("#mapload");

	// вставляем блок за картинкой, устанавливаем картинке полную прозрачность, картинку саму убираем с этой позиции в ДОМ
	img.before(wrap).css('opacity', 0).css({position: 'absolute', left: 0, top: 0, padding: 0, border: 0}).remove();
	// если это експлорер - вставляем прозрачность для експлорера
	if ($.browser.msie) {
		img.css('filter', 'Alpha(opacity=0)');
	}

	// добавляем прозрачную картинку
	wrap.append(img);
	// добавляем канвас под нашей прозрачной картой
	img.before(canvas);

	updateTask();
});

function updateTask() {
	var url = window.location.origin;
	$.ajax({
		url: url + "/imap/region/check"
	}).then(function (data) {
		if (data.length == 0) {
			drawBoilerTown("all", "yellow");
		}
		for (var i = 0; i < data.length; i++) {
			var counter = data[i];
			drawBoilerTown(counter.townId, counter.paramStatusId);
		}
	});
}

function drawBoilerTown(town, colorId) {
	var color = "yellow";
	if (colorId == 1) {
		color = "green";
	} else if (colorId == 2) {
		color = "yellow";
	} else if (colorId == 3) {
		color = "red";
	}
	switch (town) {
		case 1:
			drawTown(173, 459, "Аксай", 185, 465, color);
			break;
		case 2:
			drawTown(160, 480, "Батайск", 173, 486, color);
			break;
		case 3:
			drawTown(275, 320, "Белая Калитва", 287, 327, color);
			break;
		case 4:
			drawTown(190, 335, "Гуково", 115, 340, color);
			break;
		case 5:
			drawTown(190, 290, "Донецк", 110, 295, color);
			break;
		case 6:
			drawTown(212, 340, "Зверево", 223, 347, color);
			break;
		case 7:
			drawTown(220, 300, "Каменск", 231, 307, color);
			break;
		case 8:
			drawTown(235, 200, "Миллерово", 247, 206, color);
			break;
		case 9:
			drawTown(355, 580, "Сальск", 367, 587, color);
			break;
		case 10:
			drawTown(410, 400, "Цимлянск", 421, 406, color);
			break;
		case 11:
			drawTown(200, 385, "Шахты", 211, 391, color);
			break;
		case "all":
			drawTown(173, 459, "Аксай", 185, 465, color);
			drawTown(160, 480, "Батайск", 173, 486, color);
			drawTown(275, 320, "Белая Калитва", 287, 327, color);
			drawTown(190, 335, "Гуково", 115, 340, color);
			drawTown(190, 290, "Донецк", 110, 295, color);
			drawTown(212, 340, "Зверево", 223, 347, color);
			drawTown(220, 300, "Каменск", 231, 307, color);
			drawTown(235, 200, "Миллерово", 247, 206, color);
			drawTown(355, 580, "Сальск", 367, 587, color);
			drawTown(410, 400, "Цимлянск", 421, 406, color);
			drawTown(200, 385, "Шахты", 211, 391, color);
			break;
	}
}

function drawTown(x, y, townName, textX, textY, col) {
	web_canvas = document.getElementById('cnvs');
	var ctx = web_canvas.getContext('2d');
	ctx.beginPath();
	ctx.arc(x, y, 10, 0, Math.PI * 2, false);
	ctx.fillStyle = col;
	ctx.fill();
	ctx.stroke();
	ctx.fillStyle = "#000000";
	ctx.font = "15pt Arial";
	ctx.fillText(townName, textX, textY);
}