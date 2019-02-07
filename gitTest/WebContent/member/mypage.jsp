<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	if(session.getAttribute("login")==null){
    		request.setAttribute("msg", "로그인 후 사용 가능합니다.");
    		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
    		rd.forward(request, response);
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	p span{
		width: 100px;
		display: inline-block;
	}
	#wrap{
		width: 400px;
		margin: auto;
	}
	.image{
		width: 350px;
		height: 350px;
		border-radius: 175px;
		background-image: url("${login.photo}");
		background-size: contain;
		overflow: hidden;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
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