<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User u = (User)session.getAttribute("login");
	boolean loginStatus = u == null? false : true;
	String menuL = loginStatus ? "로그아웃" : "로그인";
	String menuH = loginStatus ? "logout.jsp" : "login.jsp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1 a{
		text-decoration: none;
		color: black;
	}
	ul{
		list-style-type: none;
		margin: 0;
		padding: 0;
		border-top: 1px solid black;
		border-bottom: 1px solid black;
	}
	
	li{
		display: inline-block;
		padding: 20px 0px;
		width: 16%;
		text-align: center;
		margin: 0;
	}
	ul a {
		text-decoration: none;
		color: black;
		font-weight: bolder;
	}
	
	ul a:hover{
		color: red;
	}
</style>
</head>
<body>
	<h1><a href="home.jsp">Open Project</a></h1>
	<nav>
		<ul>
			<li><a href="memberRegister.jsp">회원가입</a></li>
			<li><a href="<%=menuH%>"><%=menuL%></a></li>
			<li><a href="mypage.jsp">회원마이페이지(회원)</a></li>
			<li><a href="memberList.jsp">회원리스트(회원)</a></li>
			<li><a href="empList.jsp">사원리스트</a></li>
			<li><a href="EmpRegister.jsp">사원등록</a></li>
		</ul>
	</nav>
</body>
</html>