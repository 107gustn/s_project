<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<%@ include file="../default/header.jsp" %>
	
	<div class="wrap">
		<div style="width: 600px; margin: 0 auto;">
			<h3 align="center">${info.id } 정보</h3><hr>
			
			<table style="width:600px">
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
				<c:if test="${info.id == loginUser }">
				<tr>
					<td colspan="2">
						<button type="button" onclick="location.href='modify_form?id=${info.id }'">수정</button>
						<button type="button" onclick="location.href='delete?id=${info.id }'">삭제</button>
					</td>
				</tr>
				</c:if>
			</table>
			
		</div>
	</div>
	
</body>
</html>