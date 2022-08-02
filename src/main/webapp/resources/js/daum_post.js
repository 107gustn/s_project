function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            console.log( data.zonecode ) //우편번호
            console.log( data.userSelectedType ) //지번, 도로명 선택 확인
            console.log( data.roadAddress ) //도로 명주소
            console.log( data.jibunAddress ) //지번 주소
            
            //$("#addr1").val(data.zonecode)
            document.getElementById("addr1").value = data.zonecode
            if(data.userSelectedType == 'R'){
            	var addr = data.roadAddress
            }else {
            	var addr = data.jibunAddress
            }
            document.getElementById("addr2").value = addr
            
            document.getElementById("addr3").focus()
        }
    }).open();
}