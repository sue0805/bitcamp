<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
	<link rel="stylesheet" type="text/css" href="/open/resources/css/style.css">
</head>
<body>
	<h1><a href="/open">Open Project</a></h1>
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath }/signup">회원가입</a></li>
			<c:if test="${login == null }">
				<li><a href="${pageContext.request.contextPath }/login">로그인</a></li>
			</c:if>
			<c:if test="${login != null }">
				<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/member/mypage">회원마이페이지(회원)</a></li>
			<li><a href="${pageContext.request.contextPath }/member/memberList">회원리스트(회원)</a></li>
			<li><a href="${pageContext.request.contextPath }/emp/empList">사원리스트</a></li>
			<li><a href="${pageContext.request.contextPath }/emp/EmpRegister">사원등록</a></li>
			<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
		</ul>
	</nav>
</body>
</html>