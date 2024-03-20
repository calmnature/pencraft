var stompClient = null;

$(function () {
    connect();
    console.log(sessionStorage);
});

function connect() {
    var socket = new SockJS('/sockjs');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.send("/client/request/alldate",{},{})
        var daySubs= stompClient.subscribe('/process/daychart', function (data) {
            // 서버에서 받은 데이터를 refineData에 JS 객체로 보내고 리스트를 반환 받음
            // 반환값 [canvas, labelList, goodCount, badCount]
            // canvas : 그림을 그릴 공간, labelList : '2024-03'같은 라벨이 담긴 리스트
            // goodCount : 양품 수량, badCount : 불량품 수량
            var result = refineData("day", JSON.parse(data.body));

            // refineData에서 반환받은 리스트를 순서대로 drawChart의 매개변수로 전달
            drawChart(result[0], result[1], result[2], result[3], "일일 생산 현황");
            // 차트 옆에 양품, 불량품 수량을 찍어주기 위한 함수
            writeData("day",result);

            // 그래프를 그린 뒤 구독 취소 -> 구독을 해두면 해당 페이지에
            // 다른 사용자가 접속할 때마다 그래프가 새로 그려지기 때문
            daySubs.unsubscribe();
        });

        var monthSubs= stompClient.subscribe('/process/monthchart', function (data) {
            var result = refineData("month", JSON.parse(data.body));
            drawChart(result[0], result[1], result[2], result[3], "월별 생산 현황");
            writeData("month",result);
            monthSubs.unsubscribe();
        });

        var yearSubs = stompClient.subscribe('/process/yearchart', function (data) {
            var result = refineData("year", JSON.parse(data.body));
            drawChart(result[0], result[1], result[2], result[3], "연간 생산 현황");
            writeData("year",result);
            yearSubs.unsubscribe();
        });
    });
}

function refineData(classification, jsondata) {
    // 반환할 리스트 생성
    var obj = [];
    // Object.keys(JSON데이터) : 해당 JSON 데이터의 키 값만 추출
    var keys= Object.keys(jsondata);
    keys.sort(); // 정렬
    var values = []; // Key값의 Value를 저장할 리스트 생성
    var labelList = []; // Key값은 '2024-01', '2024-02'와 같은 식으로 되어 있어, 해당 키값을 저장하여 그래프의 label에 사용
    for(var key in keys){ // keys = ['2024-01','2024-02','2024-03']인데, key에는 0, 1, 2, 3 같은 인덱스가 담김
        // keys = ['2024-01','2024-02','2024-03']
        labelList.push(keys[key]); // keys[0]을 하면 '2024-01' 데이터가 출력, 해당 값을 labelList에 추가

        // jsondata = 2024-01 : { goodCount : 10, badCount : 1}, 2024-02 : {}...
        // keys[0] = '2024-01' 이므로, jsondata[keys[0]] => jsondata['2024-01']과 같음
        // json['2024-01'] = { goodCount : 10, badCount : 1}
        let data = jsondata[keys[key]];
        // 위의 양품, 불량품 데이터를 values 리스트에 추가
        values.push(data)
        // values = [{g:10,b:1}, {g:5, b:2},...{}, {}, {}, {}]
    }

    var goodCountList = []; // 양품 숫자를 저장할 리스트 추가
    var badCountList = []; // 불량품 숫자를 저장할 리스트 추가
    for(var tmp in values){ // 이것 또한 values의 사이즈만큼 0부터 반복
        // values = [{goodCount : 10, badCount : 1}, {goodCount : 5, badCount : 2}, ...]
        // values[0] = { goodCount : 10, badCount : 1}
        goodCountList.push(values[tmp]['goodCount']); // goodCount를 리스트에 추가
        badCountList.push(values[tmp]['badCount']); // badCount를 리스트에 추가
    }
    var canvas; // 그림을 그릴 캔버스 생성
    switch (classification) {
        // day, month, year에 따라 그림그릴 캔버스가 다름
        case "day" :
            canvas = $("#daycanvas");
            break;
        case "month" :
            canvas = $("#monthcanvas");
            break;
        case "year" :
            canvas = $("#yearcanvas");
            break;
    }

    // 위에서 정제한 데이터들을 obj 객체에 담아 반환
    obj.push(canvas);
    obj.push(labelList);
    obj.push(goodCountList);
    obj.push(badCountList);
    return obj;
}

function drawChart(canvas, labelList, goodCountList, badCountList, text) {
    /*
    * 아래와 같이 dataset 안에 중괄호({}) 한 블럭마다 각각 그래프가 그려짐
    * dataset : [
    * {
    *   label :
    *   data :
    * },
    * {
    *   label:
    *   data:
    * }
    * ]
    * */

    return new Chart(canvas,{
        type: 'bar',
        data: {
            labels: labelList,
            datasets: [{
                label: ['양품'],
                data: goodCountList,
                backgroundColor: '#2133DA',
                borderWidth: 1
            },{
                label: ['불량품'],
                data: badCountList,
                backgroundColor: '#DA214D',
                borderWidth: 1
            }]
        },
        options: {
            maintainAspectRatio: false,
            indexAxis: 'y',
            scales: {
                x: {
                    ticks : {
                        color: '#EDB240',
                        font: {
                            size: 10, // 텍스트 크기
                            weight: 'bold', // 텍스트 굵기
                            // fontStyle: 'italic' // 텍스트 스타일 (italic 등)
                        }
                    }
                },
                y: {
                    ticks : {
                        color: '#EDB240',
                        font: {
                            size: 15, // 텍스트 크기
                            weight: 'bold', // 텍스트 굵기
                            // fontStyle: 'italic' // 텍스트 스타일 (italic 등)
                        }
                    }
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

function writeData(classification, result){
    var pTag ;
    switch (classification) {
        case "day":
            pTag = $("#dayText")
            break;
        case "month":
            pTag = $("#monthText")
            break;
        case "year":
            pTag = $("#yearText")
            break;
    }

    console.log(result)
    for (var index in result[1]){
        pTag.append("<span class='labelText fw-bold fs-4'>" + result[1][index] + '</span><br>');
        pTag.append("양품 : " + result[2][index] +" 개<br>");
        pTag.append("불량 : " + result[3][index] +" 개<br><br>");
    }
}