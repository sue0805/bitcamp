<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="user.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="emp" class="user.Emp" scope="request"/>
    <jsp:setProperty property="*" name="emp"/>
    <%
    	int row = -1;
    	EmpDAO dao = new EmpDAO();
    	try{
    		row = dao.insertEmp(emp);
    	} catch (Exception e){
    		request.setAttribute("msg", "등록 실패");
    		e.printStackTrace();
    	}
    	if(row == -1){
    		RequestDispatcher rd = request.getRequestDispatcher("/EmpRegister.jsp");
    		rd.forward(request, response);
    	}
    	
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>등록 완료</h1>
</body>
</html>