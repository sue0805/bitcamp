<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 쓰기</title>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<form action="/write.do" method="post">
		<label for="guest_name">이름</label>
		<input type="text" name="guest_name" /><br>
		<label for="password">암호</label>
		<input type="password" name="password" /><br>
		<label for="message">내용</label>
		<textarea name="message" cols="30" rows="10"></textarea><br>
		<input type="submit" value="메시지 남기기" />
	</form>
</body>
</html>