<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

	$(function(){
		fnLogin();
	})

	$('#f').on('submit', function(event) {
		if($('#id').val() == '' || $('#pw').val() == '') {
			alert('아이디와 비밀번호를 모두 입력하세요.')
			event.preventDefault();
			return false;
		}
		// 아이디 저장 체크 확인
		// 아이디 저장은 쿠키 이용
		if($('#rememberId').is(':checked')) {
			$.cookie('rememberId', $('#id').val());
		} else {
			$.cookie('rememberId', '');
		}
		// 서브밋 수행
		return true;
	})

</script>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

	<h3>로그인</h3>

	<form action="${contextPath}/member/login" method="post">

		아이디<br>
		<input type="text" name="id" id="id"><br><br>

		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>

		<button>로그인</button><br><br>

		<label for="rememberId"><input type="checkbox" id="rememberId">아이디 저장</label>
		<label for="keepLogin"><input type="checkbox" id="keepLogin">로그인 유지</label>

	</form>

	<div>
		<a href="${contextPath}/member/findIdPage">아이디 찾기</a> | 
		<a href="${contextPath}/member/findPwPage">비밀번호 찾기</a> | 
		<a href="${contextPath}/member/agreePage">회원가입</a>
	</div>

</body>
</html>