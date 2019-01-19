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
<title>방명록 리스트</title>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
	<c:if test="${viewData.isEmpty() }">
	<p>등록된 메시지가 없습니다.</p>
	</c:if>
	<c:if test="${!viewData.isEmpty() }">
		<a href="/guestbook/write.jsp">글쓰기</a>
		<table border="1">
		<c:forEach items="${data }" var="msg">
			<tr>
				<td>메시지 번호 : ${msg.message_id }<br>
				이름 : ${msg.guest_name }<br>
				내용 : ${msg.message }<br>
				<a href="/guestbook/confirmDeletion.jsp?message_id=${msg.message_id }">[삭제하기]</a></td>
			</tr>
		</c:forEach>
		</table>
		<c:forEach items="${data }" varStatus="s" begin="1">
			<a href="/guestbookList.do?page=${s.index }">[${s.index }]</a>
		</c:forEach>
	</c:if>
	<script>
		<%
		String msg = (String)request.getAttribute("msg");
		
		if(msg != null){
		%>
		alert('${msg}');
		<%}%>
	</script>
</body>
</html>