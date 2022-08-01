<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 다음 주소 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
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
	</script>

	<%@ include file="../default/header.jsp" %>
	<h1 align="center">회원등록</h1>
	
	<div align="center">
		<form action="register" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="text" name="pw" placeholder="비밀번호"><br>
			<input type="text" readonly id="addr1" name="addr" placeholder="우편번호">
			<button type="button" onclick="daumPost()">우편번호찾기</button>
			<br>
			<input type="text" readonly id="addr2" name="addr" placeholder="주 소" >
			<input type="text" name="addr" id="addr3" placeholder="상 세 주 소">
			<br>
			<input type="submit" value="회원가입">
		</form>
	</div>
	
</body>
</html>