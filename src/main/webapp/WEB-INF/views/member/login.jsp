<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		#bt { padding:15px 10px }
	</style>

</head>
<body>

	<%@ include file="../default/header.jsp" %>
	
	<h1 align="center">로그인 페이지 입니다</h1>
	<br>
	
	<div class="wrap">
	<div align="right">
		<form action="<%=request.getContextPath() %>/member/user_check" method="post"> <!-- 절대 경로 설정 -->
			<table>
				<tr>
					<td><input type="text" name="id" placeholder="아이디"><br></td>
					<td rowspan="2"><input type="submit" value="로그인" id="bt"></td>
				</tr>
				<tr>
					<td><input type="text" name="pw" placeholder="비밀번호"><br></td>
				</tr>
				<tr>
					<td colspan="2"><a href="register_form">회원가입</a></td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" name="autoLogin">로그인 유지</td>
				</tr>
			</table>
		</form>
	</div>
	</div>

</body>
</html>