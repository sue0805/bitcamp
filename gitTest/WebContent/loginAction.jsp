<%@page import="user.User"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	UserDAO dao = new UserDAO();
	String id = (String)request.getParameter("id");
	String password = (String)request.getParameter("password");
	boolean login = dao.login(id, password) == -1 ? false : true;
	if(!login){
		request.setAttribute("msg", "로그인 실패");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	} else {
		User u = dao.userInfo(id);
		session.setAttribute("login", u);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<section>
		<h2>로그인</h2>
		<hr>
		<h5>로그인 성공!</h5>
		<a href="mypage.jsp">마이페이지 보러가기</a>
	</section>
</body>
</html>
<%}%>