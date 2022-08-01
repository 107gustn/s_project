<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="../default/header.jsp" %>
	
	<div class="wrap">
		<div style="width: 300px; margin: 0 auto;">
			<h3 align="center">${info.id } 정보</h3><hr>
			
			<table style="width:300px">
				<tr>
					<th>아이디</th>
					<td>${info.id }</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>${info.pw }</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${info.addr }</td>
				</tr>
			</table>
			
		</div>
	</div>
	
</body>
</html>