<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
	.image{
		width: 350px;
		height: 350px;
		border-radius: 175px;
		background-image: url("/open/resources/upload/${login.photo}");
		background-size: contain;
		overflow: hidden;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<section>
		<h2>회원 마이페이지</h2>
		<hr>
		<div id="wrap">
			<div class="image"></div>
			<p><span>회원번호</span>${login.idx }</p>
			<p><span>회원아이디</span>${login.id }</p>
			<p><span>회원이름</span>${login.name }</p>
		</div>
	</section>
</body>
</html>