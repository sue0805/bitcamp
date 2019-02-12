<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<section>
		<h2>이메일 인증이 필요합니다.</h2>
		<a href="/open/mail/simplemailsend">인증 메일 받기</a>
	</section>
</body>
</html>