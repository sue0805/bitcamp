<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<%System.out.println(request.getRequestURI());%><br>
	<%System.out.println(request.getContextPath());%>
</body>
</html>