<%@page import="user.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	if(session.getAttribute("login")==null){
		request.setAttribute("msg", "로그인 후 사용 가능합니다.");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	UserDAO dao = new UserDAO();  
	List<User> list = dao.getUserList();
	request.setAttribute("user", list);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				<th>회원번호</th>
				<th>회원 아이디(이메일)</th>
				<th>비밀번호</th>
				<th>회원 사진</th>
				<th>회원 이름</th>
				<th>회원가입일</th>
				<th>관리</th>
			</tr>
		<%for(int i = 0; i < list.size(); i++){ %>
			<tr>
				<td><%=list.get(i).getIdx() %></td>
				<td><%=list.get(i).getId() %></td>
				<td><%=list.get(i).getPassword() %></td>
				<td><%=list.get(i).getPhoto() %></td>
				<td><%=list.get(i).getName() %></td>
				<td><%=list.get(i).getRegDate().toString() %></td>
				<td><a href="#" class="modify" onclick="modify(<%=i%>)">수정</a> <a href="#" class="remove" onclick="remove(<%=i%>)">삭제</a></td>
			</tr>
		<%} %>
		</table>
	</section>
</body>
</html>