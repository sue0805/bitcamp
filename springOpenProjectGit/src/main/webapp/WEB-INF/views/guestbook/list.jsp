<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<c:if test="${list.isEmpty() }">
	<p>등록된 메시지가 없습니다.</p>
	</c:if>
	<c:if test="${!list.isEmpty() }">
		<a href="write">글쓰기</a>
		<table border="1">
		<c:forEach items="${list }" var="msg">
			<tr>
				<td>메시지 번호 : ${msg.no }<br>
				이름 : ${msg.gname }<br>
				내용 : ${msg.gcontent }<br>
				<a href="/guestbook/confirmDeletion.jsp?message_id=${msg.no}">[삭제하기]</a></td>
			</tr>
		</c:forEach>
		</table>
		<div class="paging">
			<c:if test="${page.prev }">
				<a href="/open/guestbook/list?pageNum=${page.startPage -1 }"> &lt;&lt; </a>
			</c:if>
			<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }">
				<a href="/open/guestbook/list?pageNum=${num }&amount=3"> ${num } </a>
			</c:forEach>
			<c:if test="${page.next }">
				<a href="/open/guestbook/list?pageNum=${page.endPage +1 }"> &gt;&gt; </a>
			</c:if>
		</div>
	</c:if>
</body>
</html>