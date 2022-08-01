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
<c:set var="contextPath" value="${ pageContext.request.contextPath }" /> <!-- 절대 경로 설정 -->

	<%@ include file="../default/header.jsp" %>
	
	<h1 align="center">회 원 정 보</h1>
	<br>
	
	<div class="wrap">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>주소</td>
			</tr>
			<c:forEach var="dto" items="${list }">
			<tr>
				<td>
					<a href="${contextPath }/member/info?id=${dto.id }">${dto.id }</a>
				</td>
				<td>${dto.pw }</td>
				<td>${dto.addr }</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>