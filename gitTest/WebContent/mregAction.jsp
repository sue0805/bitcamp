<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="userInfo" class="user.User" scope="request"/>
<jsp:setProperty name="userInfo" property="*"/>
<%
	UserDAO dao = new UserDAO();
	int signup = dao.signUp(userInfo);
	
	if(signup == -1){
		request.setAttribute("msg", "회원 가입 실패");
		RequestDispatcher rd = request.getRequestDispatcher("memberRegister.jsp");
		rd.forward(request, response);
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 완료</title>
</head>
<body>
	<header>
	<jsp:include page="/header/header.jsp"/>
	</header>
	<h2>회원가입</h2>
	<hr>
	<h5>회원 가입 성공!</h5>
	<a href="login.jsp">로그인 하러 가기</a>
</body>
</html>
<%}%>