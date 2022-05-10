<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 외부 css 파일 포함하기 -->
<link rel="stylesheet" href="/ex02/resources/css/detail.css" />
</head>

<!-- 외부 js 파일 포함하기 -->
<body>
	<script src="resources/js/jquery-3.6.0.js"></script>
	<script>
		$(document).ready(function() {
			$('.picture').on('click', function() {
				alert('2브2');
			})
		})
	</script>
	<h1>welcome to pocketmon</h1>

	<h3>이브이</h3>
	<div class="picture"></div>
</body>
</html>