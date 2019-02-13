<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h2>이메일 인증</h2>
		<c:if test="${msg == null }">
		인증 메일이 성공적으로 보내졌습니다.<br>
		인증 후 사이트 이용이 가능합니다.
		</c:if>
		<c:if test="${msg != null }">
		${msg }
		</c:if>
	</section>
</body>
</html>