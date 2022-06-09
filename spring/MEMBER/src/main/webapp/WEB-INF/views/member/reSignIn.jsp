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
<script>

	$(function(){
		fnReSignIn();
	})
	
	let regPw = /^[0-9]{1,4}$/;

	function fnReSignIn(){
		$('#f').on('submit', function(event) {
			if(regPw.test($('#pw').val()) == false) {
				alert('1~4자 숫자를 사용하세요.')
				event.preventDefault();
				return false;
			} else if($('#pwConfirm').val() != '' && $('#pw').val() != $('#pwConfirm').val()){
				alert('비밀번호를 확인해주세요.');
			}
			return true;
		})
	}

</script>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

	<form id="f" action="${contextPath}/member/reSignIn" method="post">

		가입 아이디<br>
		${member.id}<br><br>

		신규 비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>

		비밀번호 확인<br>
		<input type="password" id="pwConfirm"><br><br>

		가입자명<br>
		<input type="text" name="name" value="${member.name}"><br><br>

		이메일<br>
		${member.email.substring(0,3)}***${member.email.substring(member.email.indexOf('@'))}

		탈퇴일<br>
		${member.signOut}<br><br>

		<input type="hidden" name="memberNo" value="${member.memberNo}">
		<input type="hidden" name="id" value="${member.id}">
		<input type="hidden" name="email" value="${member.email}">
		<input type="hidden" name="agreeState" value="${member.agreeState}">

		<button>사이트 다시 이용하기</button>

	</form>

</body>
</html>