
//선택된 제품의 데이터를 모달에 전달 해주기위한 자바 스크립트(매우매우 비효율적)
function openModal(productId, lotId, inkCapacity, nibDepth, assemblyBody, assemblyCap, acceptance, createdDate, modifiedDate, comment) {
// 선택한 제품의 데이터를 모달에 전달
document.getElementById('productId').innerText = productId;
document.getElementById('lot_id').innerText = lotId;
document.getElementById('ink_capacity').innerText = inkCapacity;
document.getElementById('nib_depth').innerText = nibDepth == "null" ? '미결합' : nibDepth + "mm";
document.getElementById('assembly_body').innerText = assemblyBody === "Y" ? '결합' : '미결합';
document.getElementById('assembly_cap').innerText = assemblyCap  === "Y" ? '결합' : '미결합';

//기존
// document.getElementById('acceptance').innerText = acceptance  === 1 ? '적합' : '부적합';

//1차 시도 : 항상 부적합에 checked되어서 실패
// document.getElementById('acceptanceG').checked = acceptance === 1;
// document.getElementById('acceptanceB').checked = acceptance !== 0;

//2차 시도 : 성공!
if (acceptance === "P") {
document.getElementById('acceptanceG').checked = true;
document.getElementById('acceptanceB').checked = false;
document.getElementById('comment').disabled = true;

} else {
document.getElementById('acceptanceG').checked = false;
document.getElementById('acceptanceB').checked = true;
document.getElementById('comment').disabled = false;
}
document.getElementById('start_date').innerText = createdDate;
document.getElementById('end_date').innerText = modifiedDate;

//기존에 있던 코멘트를 가져오기 위한 코드
//그러나 코멘트가 없으면 null을 가져와서 null 띄운다. -> null이면 아무것도 안뜨게할 수 없을까?
// document.getElementById('comment').innerText = comment;

//수정본 => 조건을 단다.
//조건식이 "null" 인이유 -> 그냥 null이면 인식을 못함. 왜? 넘어오는 commnet값이 string값으로 넘어오는 듯 하다. null을 문자 그대로 받아와서 어찌됐든 값이 있다고 생각한다.
// if (comment !== "null") {
//     console.log(comment)
//     console.log("코멘트 값이 있다.")
//     document.getElementById('comment').innerText = comment;
// } else {
//     document.getElementById('comment').innerText ="";
// }

//더 깔끔하게 줄인 코드
document.getElementById('comment').innerText = comment;
if (comment === "null") {
    document.getElementById('comment').innerText ="";
}

// 모달 표시
$('#qcModal').modal('show');
// productId를 hidden 필드에 설정
    document.getElementById('product_id').value = productId;
}

function saveComment() {
// Form을 서버에 제출
    document.querySelector('form').submit();
}

document.querySelectorAll('input[type="radio"]').forEach(function(radio) {
    radio.addEventListener('change', function() {
        console.log(this)
        console.log(this.value)
        // var acceptanceValue = this.value;
        var commentTextarea = document.getElementById('comment');

        // 부적합일 경우 textarea 활성화, 아닐 경우 비활성화
        commentTextarea.disabled = (this.value === "P");

        if (this.value === "P") {
            commentTextarea.value = ""; // textarea 내용 리셋
            console.log("실행완료")
        }
    });
});


