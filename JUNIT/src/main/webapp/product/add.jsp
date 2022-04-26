<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function){
		fnFileCheck();
	})
	
	function reset(){
		$('#reset').val('');
	}
	
	function fnFileCheck(){
		//	파일을 첨부하면 change 이벤트 동작
		$('#filename').change(function(){
			//	확장자 제한
			var origin = $(this).val().toLowerClase();
			var regExp = /(.*?)\.(jpg|gif|png|tif)$;
			if(regExp.test(origin) == false) {
				alert('확장자가 jpg, jif, png, tif인 이미지만 첨부 가능합니다.');
				$(this).val('');
				return;
			}
			//	크기 제한
			var MaxSize = 1024 * 1024 * 10;
			var fileSize = $(this)[0].files[0].size;
			if(fileSize > MaxSize) {
				alert('첨부 파일의 최대 크기는 10MB입니다.');
				$(this).val('');
				return;
			}
		})
	}
</script>
</head>
<body>
	<form action="/JUNIT/add.do" method="post" enctype="multipart/form-data">
		<div>
			<input type="text" name="name" placeholder="제품명">
		</div>
		<div>
			<input type="text" name="price" placeholder="제품가격">
		</div>
		<div>
			<input type="file" name="filename" id="filename">
		</div>
		<div>
			<button>등록</button>
			<input type="reset" value="다시작성" onclick="reset()">
			<input type="button" value="목록" onclick="location.href='/list.do'">
		</div>
	</form>
</body>
</html>