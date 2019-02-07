<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<h2>회원가입</h2>
	<hr>
	<form class="rform" action="/open/signup" enctype="multipart/form-data" method="POST">
		<label class="rlabel" for="id">아이디(이메일)</label>
		<input type="text" name="id" id="id" /><br>
		<label class="rlabel" for="password">비밀번호</label>
		<input type="password" name="password" id="password" /><br>
		<label class="rlabel" for="name">이름</label>
		<input type="text" name="name" id="name" /><br>
		<label class="rlabel" for="photo">사진</label>
		<input type="file" name="photo" id="photo" /><br>
		<input class="rsubmit" type="submit" value="등록" />
	</form>
	<c:if test="${msg != null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
</body>
</html>