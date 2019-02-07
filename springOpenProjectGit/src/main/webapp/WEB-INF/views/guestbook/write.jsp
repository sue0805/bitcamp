<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 쓰기</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<form action="/open/guestbook/write" method="post">
		<label for="gname">이름</label>
		<input type="text" name="gname" /><br>
		<label for="gpassword">암호</label>
		<input type="password" name="gpassword" /><br>
		<label for="gcontent">내용</label>
		<textarea name="gcontent" cols="30" rows="10"></textarea><br>
		<input type="submit" value="메시지 남기기" />
	</form>
	<c:if test="${msg != null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
</body>
</html>