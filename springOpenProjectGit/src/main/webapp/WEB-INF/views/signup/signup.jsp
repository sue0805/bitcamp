<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"/>
	</header>
	<h2>회원가입</h2>
	<hr>
	<form class="rform">
		<label class="rlabel" for="id">아이디(이메일)</label>
		<input type="text" name="id" id="id" /><br>
		<label class="rlabel" for="password">비밀번호</label>
		<input type="password" name="password" id="password" /><br>
		<label class="rlabel" for="name">이름</label>
		<input type="text" name="name" id="name" /><br>
		<label class="rlabel" for="photo">사진</label>
		<input type="file" name="photo" id="photo" /><br>
		<input class="rsubmit" type="button" value="등록" />
	</form>
	<c:if test="${msg != null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
	<script>
		$('.rsubmit').click(function(){
			var formData = new FormData();
			var inputFile = $('#photo');
			var file = inputFile[0].files[0];
			
			console.log(file);
			formData.append('id', $('#id').val());
			formData.append('password', $('#password').val());
			formData.append('name', $('#name').val());
			formData.append('photo', file);
			
			for (var p of formData) {
				  console.log(p);
			}
			
			$.ajax({
				type : 'post',
				url : '/open/join',
				data : formData,
				contentType : false,
				processData : false,
				success : function(result){
					if(result == '1'){
						alert('회원 가입 성공! 이메일 인증 후 사이트 사용 가능합니다.');
						location.href='/open/login';
					} else {
						alert('회원 가입 성공! 인증 메일 발송에 실패했습니다. 문의하세요.');
					}
				},
				error : function(data){
					alert('회원 가입 실패');
				}
			});
		});
	</script>
</body>
</html>