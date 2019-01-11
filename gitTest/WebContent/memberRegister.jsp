<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width: 150px;
		display: inline-block;
		margin: 5px 0px;
	}
	
	form{
		margin: auto;
		width: 450px;
	}
	
	input[type=submit]{
		width: 100px;
		margin: 10px auto;
		display: block;
	}
	
</style>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<h2>회원가입</h2>
	<hr>
	<form action="mregAction.jsp" method="POST">
		<label for="id">아이디(이메일)</label>
		<input type="text" name="id" id="id" /><br>
		<label for="password">비밀번호</label>
		<input type="password" name="password" id="password" /><br>
		<label for="name">이름</label>
		<input type="text" name="name" id="name" /><br>
		<label for="photo">사진</label>
		<input type="file" name="photo" id="photo" /><br>
		<input type="submit" value="등록" />
	</form>
	<script>
		<%if(request.getAttribute("msg") != null){%>
			alert('${msg}');
		<%}%>
	</script>
</body>
</html>