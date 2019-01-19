<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	if(session.getAttribute("login")==null){
		request.setAttribute("msg", "로그인 후 사용 가능합니다.");
		RequestDispatcher rd = request.getRequestDispatcher("/member/login.jsp");
		rd.forward(request, response);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
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
			<c:forEach items="${user }" var="member" varStatus="stat">
				<tr>
					<td>${member.idx }</td>
					<td>${member.id }</td>
					<td>${member.password }</td>
					<td>${member.photo }</td>
					<td>${member.name }</td>
					<td>${member.regDate.toString() }</td>
					<td><a href="#" class="modify" onclick="modify(${stat.index})">수정</a> <a href="#" class="remove" onclick="remove(${stat.index})">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<script>
	
		function modify(no){
			location.href = "/member/editMember.jsp?idx="+no;
		}
		
		function remove(no){
			location.href = "/member/delMember.do?idx="+no;
		}
		
	</script>
</body>
</html>