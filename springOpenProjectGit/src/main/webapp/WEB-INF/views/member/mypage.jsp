<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
	.image{
		width: 350px;
		height: 350px;
		border-radius: 175px;
		background-size: contain;
		overflow: hidden;
	}
</style>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<section>
		<h2>회원 마이페이지</h2>
		<hr>
		<div id="wrap">
			<div class="image"></div>
			<p><span>회원번호</span><span id="idx"></span></p>
			<p><span>회원아이디</span><span id="id"></span></p>
			<p><span>회원이름</span><span id="name"></span></p>
		</div>
	</section>
	<script>
		$(document).ready(function(){
			$.getJSON('/open/member/info.json', function(member){
				$('#idx').text(member.idx);
				$('#id').text(member.id);
				$('#name').text(member.name);
				$('.image').css('backgroundImage', 'url("/open/resources/upload/'+member.photo+'")');
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
		});
	</script>
</body>
</html>