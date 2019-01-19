<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	label{
		width: 150px;
		display: inline-block;
		margin: 10px 0px;
	}
	
	input{
		padding: 5px 0;
	}
	
	form{
		width: 450px;
		margin: auto;
	}
	
	input[type=submit]{
		width: 80px;
		margin-left: 155px;
		display: block;
		margin-top: 20px;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<section>
		<h2>회원 로그인</h2>
		<hr>
		<form action="/login.do" method="POST">
			<label for="id">아이디(이메일)</label>
			<input type="text" name="id" id="id" required/><br>
			<label for="password">비밀번호</label>
			<input type="password" name="password" id="password" required/><br>
			<input type="submit" value="로그인" />
		</form>
	</section>
	<script>
		<%
			if(request.getAttribute("msg") != null){
		%>
				alert('${msg}');
		<%}%>
	</script>
</body>
</html>