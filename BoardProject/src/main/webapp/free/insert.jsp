<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function reset(){
		$('#reset').val('');
	}
</script>
</head>
<body>
	<h1>자유게시판 게시글 작성화면</h1>
	<form action="/BoardProject/insert.do" method="post">
		<input type="text" name="writer" id="writer"><br>
		<input type="text" name="title" id="title"><br>
		<textarea name="content" id="content" cols="20" rows="2"></textarea><br>
		<button>작성완료</button>
		<input type="reset" value="다시작성" id="reset" onclick="reset()">
		<input type="button" value="목록" onclick="location.href='/BoardProject/list.do'">
	</form>
</body>
</html>