<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		#modal_wrap { /* 댓글 폼 배경 */
			display: none; /* 숨김처리 */
			position: fixed; /* fixed: 특정위치 고정 */
			z-index: 9; /* 순서지정 */
			margin: auto; /* auto: 가운데 정렬 */
			top:0; left:0; right:0; width: 100%; height: 100%;
			background-color: rgba(0, 0, 0, 0.4);
		}
		#first {
			display: none;
			position: fixed; z-index: 10; margin: auto;
			top:30px; left:0; right:0; width: 350px; height: 300px;
			background-color: rgba(209, 178, 255, 0.9);
		}
	</style>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> <!-- jquery cdn -->
	<script type="text/javascript">
		function slideClick() {
			$("#first").slideDown("slow"); //천천히 보여줌
			$("#modal_wrap").show(); //바로 보여줌
		}
		function slide_hide() {
			$("#title").val(""); //입력값 지움
			$("#content").val("");
			
			$("#first").hide(); //창 숨기기
			$("#modal_wrap").hide();
		}
		
		function rep() {
			/*
			let form = { // 아이디를 통해서 사용자가 입력한 값을 가져옴
					write_no : $("#write_no").val(),
					title : $("#title").val(),
					content : $("#content").val()
					}
			console.log( form )
			*/
			let form = {}
			let arr = $("#frm").serializeArray(); //arrayList 배열형태로 가져옴 //name: 키 , value: 값
			console.log( arr )
			
			for(i=0; i<arr.length; i++) {
				form[arr[i].name] = arr[i].value
			} //키, 값을 한쌍으로 만들어줌
			console.log( form )
			$.ajax({
				url : "addReply", //경로이동
				type : "post", //전송 방식
				data: JSON.stringify(form),//넘겨주는 데이터 //javascript 객체의 object타입에서 문자열로 바꿔줌
				contentType : "application/json;charset=utf-8", //타입
				success : function() {
					alert("답글이 달렸습니다");
					slide_hide();
					replyData();
				}
				
			})
		}
		
		function replyData() { //데이터를 가져옴
			$.ajax({
				//url : "replyData?writeNo=" + ${dto.writeNo}
				//컨트롤러 매개변수 int writeNo 받으면 된다.
				
				url : "replyData/" + ${dto.writeNo},
				type:"get", //전송 방식
				dataType : "json", //controller에서 return으로 돌려주는 타입
				success : function( rep ) { //지정한 json 형식으로 들어옴
					console.log( rep )
					let html = ""
					for( i=0; i<rep.length; i++) {
						let date = new Date( rep[i].write_date ) //초단위의 날짜 데이트를 변경시켜줌
						let wd = date.getFullYear() + "년";
						wd += (date.getMonth() + 1) + "월"; //0월부터 시작
						wd += date.getDate() + "일"
						wd += date.getHours() + "시"
						wd += date.getMinutes() + "분"
						wd += date.getSeconds() + "초"
						
						html += "<div align='left'><b>아이디 : </b>" + rep[i].id + "님 / "
						html += "<b>작성일 : </b>" + wd + "<br>"
						html += "<b>제목 : </b>" + rep[i].title + "<br>"
						html += "<b>내용 : </b>" + rep[i].content + "<hr></div>"
					}
					$("#reply").html( html )
				}
			})
			
		}
	</script>

</head>
<body onload="replyData();">

	<div id="modal_wrap">
	<div id="first"> <!-- 큰 범위 지정: id, 종속되어있는 내용: class -->
		
		<div style="width:250px; margin:auto; padding-top:20px;">
			<form id="frm"> <!-- 많은 데이터 처리는 form이 편하다 -->
				<input type="hidden" name="write_no" id="write_no" value="${dto.writeNo }">
				
				<b>답글 작성 페이지</b>
				<hr>
				<b>작성자 : </b>${loginUser }
				<hr>
				<b>제목</b><br>
				<input type="text" id="title" name="title" size="30"><hr>
				<b>내용</b><br>
				<textarea rows="5" cols="30" name="content" id="content"></textarea>
				<hr>
				
				<button type="button" onclick="rep()">답글</button>
				<button type="button" onclick="slide_hide()">취소</button>
			</form>
		</div>
		
	</div>
	</div>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <!-- 절대경로 생성 -->
	
	<c:import url="../default/header.jsp" />
	
	<div class="wrap">
	<div style="margin: auto; width:500px;"> <!-- margin: auto - 가운데 정렬 -->
	
	<table border="1" align="center"> <!-- 테이블 생성후 가운데 정렬 -->
		<caption><font size="5"><b>개인 정보</b></font></caption> <!-- 제목부분에 붙음 -->
		<tr>
			<th width="100">글 번호</th> <td width="200">${dto.writeNo}</td> 
			<th width="100">작성자</th>  <td width="200">${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th> <td>${dto.title}</td> 
			<th>등록일자</th> <td>${dto.saveDate}</td>
		</tr>
		<tr>
			<th>내용</th> <td>${dto.content}</td> 
			<td colspan="2">
			<c:if test="${ dto.imageFileName == 'nan' }">
				<b>이미지가 없습니다</b>
			</c:if>
			<c:if test="${ dto.imageFileName != 'nan' }">
				<img width="200px" height="100px" src="${contextPath}/board/download?imageFileName=${dto.imageFileName}">
			</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
      		<c:if test="${ loginUser == dto.id }">
				<input type="button" onclick="location.href='modify_form?writeNo=${dto.writeNo}'" value="수정하기"> 
				<input type="button" onclick="location.href='delete?writeNo=${dto.writeNo}&imageFileName=${dto.imageFileName }'" value="삭제하기">
			</c:if>
			<input type="button" onclick="slideClick()" value="답글달기"> <!-- JSP페이지 이동하지않고 ajax를 이용하여 처리 -->
			<input type="button" onclick="location.href='boardAllList'" value="리스트로 돌아가기">
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div id="reply"></div>
			</td>
		</tr>
	</table>
	
	</div>
	</div>

</body>
</html>