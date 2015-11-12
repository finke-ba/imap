
$(document).ready(function drow(){
    var img = $('#imgmap');
    var canvas=$('#cnvs');
    //setInterval('updateTask()', 3000)
    canvas.css({position: 'absolute',left: 0,top: 0,padding: 0,border: 0});
    //добавляем div
    // делаем ему фоновой картинкой нашу карту, ее ширину и высоту передаем блоку
    // добавляем этот блок в наш уже существующий

    wrap = $('<div>').css({display:'block',background:'url('+img.attr('src')+')',position:'relative',padding:0,width:img.width(),height:img.height()}).attr('id','canvas_div').appendTo("#mapload");

    // вставляем блок за картинкой, устанавливаем картинке полную прозрачность, картинку саму убираем с этой позиции в ДОМ
    img.before(wrap).css('opacity', 0).css({ position: 'absolute',left: 0,top: 0,padding: 0,border: 0 }).remove();
    // если это експлорер - вставляем прозрачность для експлорера
    if($.browser.msie) { img.css('filter', 'Alpha(opacity=0)'); }

    // добавляем прозрачную картинку
    wrap.append(img);
    // добавляем канвас под нашей прозрачной картой
    img.before(canvas);
    // рисуем необходимые пикгограммы
    drawBoiler("all", "yellow");


    updateTask();
});

function updateTask(){
    $.ajax({
        url: "http://localhost:8080/imap/get/boilers/region/check/new"
    }).then(function(data) {
        for (var i = 0; i < data.length; i++) {
            var counter = data[i];
            drawBoiler(counter.townId, counter.paramStatusId);
        }
    });
}

function drawBoiler(town, colorId){
    var color = "yellow";
    if (colorId == 1) {
        color = "green";
    } else if (colorId == 2) {
        color = "yellow";
    } else if (colorId == 3) {
        color = "red";
    }
    switch (town) {
        case "all":
            drawAksay(color);
            drawBataysk(color);
            drawBelayaKalitva(color);
            drawGukovo(color);
            drawDoneck(color);
            drawZverevo(color);
            drawKamensk(color);
            drawMillerovo(color);
            drawSalsk(color);
            drawCimlyansk(color);
            drawShachti(color);
            break;
        case 1:
            drawAksay(color);
            break;
        case 2:
            drawBataysk(color);
            break;
        case 3:
            drawBelayaKalitva(color);
            break;
        case 4:
            drawGukovo(color);
            break;
        case 5:
            drawDoneck(color);
            break;
        case 6:
            drawZverevo(color);
            break;
        case 7:
            drawKamensk(color);
            break;
        case 8:
            drawMillerovo(color);
            break;
        case 9:
            drawSalsk(color);
            break;
        case 10:
            drawCimlyansk(color);
            break;
        case 11:
            drawShachti(color);
            break;
        default:
        //alert("Defaulst switch")
    }
}

function drawAksay(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(173,459,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Аксай", 185, 465);
}

function drawBataysk(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(160,480,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Батайск", 173, 486);
}

function drawBelayaKalitva(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(275,320,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Белая Калитва", 287, 327);
}

function drawGukovo(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(190,335,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Гуково", 115, 340);
}

function drawDoneck(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(190,290,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Донецк", 110, 295);
}

function drawZverevo(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(212,340,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Зверево", 223, 347);
}

function drawKamensk(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(220,300,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Каменск", 231, 307);
}

function drawMillerovo(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(235,200,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Миллерово", 247, 206);
}

function drawSalsk(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(355,580,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Сальск", 367, 587);
}

function drawCimlyansk(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(410,400,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Цимлянск", 421, 406);
}

function drawShachti(col){
    web_canvas = document.getElementById('cnvs');
    var ctx = web_canvas.getContext('2d');
    ctx.beginPath();
    ctx.arc(200,385,10,0,Math.PI*2,false);
    ctx.fillStyle = col;
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = "#000000";
    ctx.font = "15pt Arial";
    ctx.fillText("Шахты", 211, 391);
}