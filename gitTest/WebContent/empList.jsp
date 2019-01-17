<%@page import="java.util.List"%>
<%@page import="user.Emp"%>
<%@page import="user.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
	    if(session.getAttribute("login")==null){
			request.setAttribute("msg", "로그인 후 사용 가능합니다.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
    
	    EmpDAO dao = new EmpDAO();
	    List<Emp> list = dao.selectEmp();
	    
	    pageContext.setAttribute("list", list);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	table{
		margin: 30px auto;
		width: 1000px;
		border: 1px solid black;
	}
	th, td{
		border: 1px solid black;
		padding: 5px;
		text-align: center;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<section>
	<h2>회원 리스트</h2>
		<table>
			<tr>
				<th>EMPNO</th>
				<th>NAME</th>
				<th>JOB</th>
				<th>MANAGER</th>
				<th>HIREDATE</th>
				<th>SALARY</th>
				<th>COMM</th>
				<th>DEPTNO</th>
				<th>관리</th>
			</tr>
			<c:forEach items="${list}" var="emp">
				<tr>
					<td>${emp.empno }</td>
					<td>${emp.ename }</td>
					<td>${emp.job }</td>
					<td>${emp.mgr }</td>
					<td>${emp.hiredate}</td>
					<td>${emp.sal }</td>
					<td>${emp.comm }</td>
					<td>${emp.deptno }</td>
					<td><a href="#" class="modify" onclick="modify(${emp.empno})">수정</a> <a href="#" class="remove" onclick="remove(${emp.empno})">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<script>
		function modify(no){
			var popUrl = 'editEmp.jsp?empno='+no;
			var popOption = 'width=370, height=400, resizable=no, scrollbars=no, status=no'
			window.open(popUrl, "", popOption);
		}
		
		function remove(no){
			var del = confirm('삭제하시겠습니까?');
			if(del == true){
				location.href = 'removeEmpAction.jsp?empno='+no;
			}
		}
	</script>
</body>
</html>