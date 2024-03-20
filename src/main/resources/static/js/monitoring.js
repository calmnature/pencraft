var stompClient = null;
// new Chart() 객체를 저장할 변수
var chartOne = null;
var chartTwo = null;
var chartThree = null;
var chartFour = null;

// 1~4공정의 각각의 캔버스를 담을 변수
var canvasOne;
var canvasTwo;
var canvasThree;
var canvasFour;

$(function () {
    canvasOne = $("#processOne");
    canvasTwo = $("#processTwo");
    canvasThree = $("#processThree");
    canvasFour = $("#processFour");
    connect();
});

function connect() {
    var socket = new SockJS('/sockjs');
    console.log("SockJS 생성");

    stompClient = Stomp.over(socket);
    console.log("Stomp 위에 SockJS 올림");


    stompClient.connect({}, function () {
        // alert("WebSocket(SockJS)이 연결되었습니다.");
        var lastData1 = null;
        var lastData2 = null;
        var lastData3 = null;
        var lastData4 = null;
        stompClient.send("/client/request",{},{})
        var subscription = stompClient.subscribe('/process/lastdata', function (data) {
            console.log(data);
            console.log(data.body);
            console.log(JSON.parse(data.body));
            var jsondata = JSON.parse(data.body);
            if(jsondata.processOne != null){
                lastDataDraw(jsondata);
            }
            subscription.unsubscribe();
        });

        stompClient.subscribe('/process/first', function (data) {
            if(chartTwo !== null){
                chartTwo.destroy();
                chartThree.destroy();
                chartFour.destroy();
                $("#secondText").html("");
                $("#thirdText").html("");
                $("#fourthText").html("");
            }
            var newData1 = JSON.parse(data.body);
            console.log(newData1);
            console.log(lastData1);
            if(!compareData(newData1, lastData1)){
                chartOne = drawChart(canvasOne,JSON.parse(data.body),"1공정");
                resultData($("#firstText"), JSON.parse(data.body));
                lastData1 = newData1;
            }
        });


        stompClient.subscribe('/process/second', function (data) {
            console.log(data);
            console.log(chartTwo);
            var newData2 = JSON.parse(data.body);
            if(!compareData(newData2, lastData2)){
                chartTwo = drawChart(canvasTwo,JSON.parse(data.body),"2공정");
                resultData($("#secondText"), JSON.parse(data.body));
                lastData2 = newData2;
            }
        });

        stompClient.subscribe('/process/third', function (data) {
            console.log(data);
            var newData3 = JSON.parse(data.body);
            if(!compareData(newData3, lastData3)){
                chartThree = drawChart(canvasThree,JSON.parse(data.body),"3공정");
                resultData($("#thirdText"), JSON.parse(data.body));
                lastData3 = newData3;
            }
        });

        stompClient.subscribe('/process/fourth', function (data) {
            console.log(data);
            var newData4 = JSON.parse(data.body);
            if(!compareData(newData4, lastData4)){
                chartFour = drawChart(canvasFour,JSON.parse(data.body),"4공정");
                resultData($("#fourthText"), JSON.parse(data.body));
                lastData4 = newData4;
            }
        });
    });
}


function drawChart(canvas, data, text) {
    var canvasId = canvas[0].id;
    var chartStatus = Chart.getChart(canvasId);
    if(chartStatus != undefined)
        chartStatus.destroy();
    return new Chart(canvas,{
        type: 'pie',
        data: {
            labels: ['양품', '불량품'],
            datasets: [{
                label: '수량',
                data: [data.goodCount, data.badCount],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    display:false
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: text,   //제목 "1공정"
                    position: 'top',
                    align: 'center',
                    font: {
                        size: 30,
                        weight: 'bold',
                    },
                    color: 'white'
                }
            }
        }
    });
}

function lastDataDraw(jsondata) {
    // Chart 초기화
    var select = jsondata.processOne.nowProcess;
    switch (select) {
        case 1:
            break;
        case 2:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            resultData($("#firstText"), jsondata.processOne);
            break;
        case 3:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            break;
        case 4:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            chartThree = drawChart(canvasThree, jsondata.processThree, "3공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            resultData($("#thirdText"), jsondata.processThree);
            break;
        case 0:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            chartThree = drawChart(canvasThree, jsondata.processThree, "3공정");
            chartFour = drawChart(canvasFour, jsondata.processFour, "4공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            resultData($("#thirdText"), jsondata.processThree);
            resultData($("#fourthText"), jsondata.processFour);
            break;
    }
}

function resultData(pTag, jsondata) {
    pTag.html("<br><b>" + "양품 : " + jsondata.goodCount + "개</b><br><br>" + "<b>불량품 : " + jsondata.badCount + "개</b>");
}

function compareData(newData, lastData){
    if(lastData !== null && JSON.stringify(lastData) === JSON.stringify(newData)) {
        return true;
    }
    return false;
}