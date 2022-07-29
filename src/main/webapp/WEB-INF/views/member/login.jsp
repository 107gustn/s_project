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
	<div align="right">
		<form action="<%=request.getContextPath() %>/member/user_check" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="text" name="pw" placeholder="비밀번호"><br>
			<input type="submit" value="로그인">
			<a href="#">회원가입</a>
		</form>
	</div>
	</div>

</body>
</html>