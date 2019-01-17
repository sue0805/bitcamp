<%@page import="user.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String empno = request.getParameter("empno");

	EmpDAO dao = new EmpDAO();
	int row = dao.deleteEmp(empno);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		<%if(row == -1){%>
			alert('삭제 실패');
			history.go(-1);
		<%} else {%>
			alert('삭제 성공');
			location.href = 'empList.jsp';
		<%}%>
	</script>
</body>
</html>