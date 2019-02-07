<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
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
			<c:forEach items="${list }" var="member" varStatus="stat">
				<tr>
					<td>${member.idx }</td>
					<td>${member.id }</td>
					<td>${member.password }</td>
					<td><img src="/open/resources/upload/${member.photo }" alt="member_photo"></td>
					<td>${member.name }</td>
					<td>${member.regDate.toString() }</td>
					<td><a href="#" class="modify" onclick="modify(${member.idx})">수정</a> <a href="#" class="remove" onclick="remove(${member.idx})">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>