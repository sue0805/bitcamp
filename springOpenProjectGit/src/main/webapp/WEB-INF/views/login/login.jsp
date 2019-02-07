<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<section>
		<h2>회원 로그인</h2>
		<hr>
		<form action="/open/login" method="POST">
			<label for="id">아이디(이메일)</label>
			<input type="text" name="id" id="id" required/><br>
			<label for="password">비밀번호</label>
			<input type="password" name="password" id="password" required/><br>
			<input type="submit" value="로그인" />
		</form>
	</section>
	<c:if test="${msg != null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
</body>
</html>