<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<form>
		<label class="rlabel" for="id">아이디(이메일)</label>
		<input type="text" name="id" id="id" /><br>
		<label class="rlabel" for="password">비밀번호</label>
		<input type="password" name="password" id="password" /><br>
		<label class="rlabel" for="name">이름</label>
		<input type="text" name="name" id="name" /><br>
		<label class="rlabel" for="photo">사진</label>
		<input type="file" name="photo" id="photo" /><br>
		<input type="button" id="btn" value="submit"/>
	</form>
	
	<script>
		function member(id, photo){
			this.id = id;
			this.photo = photo;
		}
		
		var mem;
		var files;
		var formData;
		var inputFile;
		
		$('#btn').click(function(){
			
			formData = new FormData();
			inputFile = $('#photo');
			file = inputFile[0].files[0];
			
			console.log(files);
			formData.append('id', $('#id').val());
			formData.append('password', $('#password').val());
			formData.append('name', $('#name').val());
			formData.append('photo', file);
			
			for (var p of formData) {
				  console.log(p);
			}
			
			$.ajax({
				type : 'post',
				url : '/open/member/test001',
				data : formData,
				contentType : false,
				processData : false,
				success : function(result){
					
				},
				error : function(data){
					$('body').append(data.responseText);
				}
			});
		});
	</script>
</body>
</html>