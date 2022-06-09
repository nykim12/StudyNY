<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	.ok {
		color: limegreen;
	}

	.dont {
		color: crimson;
	}

</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
		fnIdCheck();
		fnPwCheck();
		fnPwConfirm();
		fnEmailAuth();
		fnToUpperCase();
		fnSignIn();
	})

	// 1. 아이디 정규식 & 중복체크
	let idPass = false;
	function fnIdCheck(){
		$('#id').on('keyup', function(){
			let regId = /^[a-z]{1,32}$/;
			if(regId.test($('#id').val()) == false) {
				$('#idMsg').text('1~32자의 영어 소문자만 사용 가능합니다.').addClass('dont').removeClass('ok');
				idPass = false;
				return;
			}
			// 아이디 중복 체크
			$.ajax({
				url: '${contextPath}/member/idCheck',
				type: 'get',
				data: 'id=' + $('#id').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res == null){
						$('#idMsg').text('사용 가능한 아이디입니다.').addClass('ok').removeClass('dont');
						idPass = true;
					} else {
						$('#idMsg').text('이미 사용 중이거나 탈퇴한 아이디입니다.').addClass('dont').removeClass('ok');
						idPass = false;
					}
				},
				error: function(jqXHR){
					$('#idMsg').text(jqXHR.responseText).addClass('dont').removeClass('ok');
					idPass = false;
				}
			})
		})
	}

	// 2. 비밀번호 정규식 확인
	let pwPass = false;
	function fnPwCheck(){
		$('#pw').on('keyup', function(){
			let regPw = /^[0-9]{1,4}$/;
			if(regPw.test($('#pw').val()) == false) {
				$('#pwMsg').text('1~4자 숫자를 사용하세요.').addClass('dont').removeClass('ok');
				pwPass = false;
			} else {
				$('#pwMsg').text('사용 가능한 비밀번호입니다.').addClass('ok').removeClass('dont');
				pwPass = true;
			}
		})
	}

	// 3. 비밀번호 일치 확인
	let rePwPass = false;
	function fnPwConfirm(){
		$('#pwConfirm').on('keyup', function(){
			if($('#pwConfirm').val() != '' && $('#pw').val() != $('#pwConfirm').val()) {
				$('#pwConfirmMsg').text('비밀번호를 확인해주세요.').addClass('dont').removeClass('ok');
				rePwPass = false;
			} else {
				$('#pwConfirmMsg').text('');
				rePwPass = true;
			}
		})
	}

	// 4. 이메일 정규식 확인 및 중복 체크
	function fnEmailCheck(){
		return new Promise(function(resolve, reject) {
			// 1) 이메일 정규식 확인, (\.[a-zA-Z]{2,}){1,2} : .com / .net 또는 .co.kr / .or.kr 등의 문자 표현
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;
			if(regEmail.test($('#email').val()) == false) {
				reject(1000);	// 이메일 형식이 잘못된 경우의 에러 코드 - 1000
				return;
			}
			// 2) 이메일 중복 체크
			$.ajax({
				url: '${contextPath}/member/emailCheck',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res == null){
						resolve();		// Promise 객체의 then 메소드에 전달되는 함수
					} else {
                       	reject(2000);	// 이메일 중복된 경우의 에러 코드 - 2000
					}
				}
			})
		});
	}

	// 5. 이메일 인증 코드 전송
	function fnEmailAuth(){
		$('#btnGetAuthCode').on('click', function(){
			fnEmailCheck().then(
				function(){
					$.ajax({
						url: '${contextPath}/member/sendAuthCode',
						type: 'get',
						data: 'email=' + $('#email').val(),
						dataType: 'json',
						success: function(obj){ // obj에는 발송한 인증코드가 저장되어 있음
							alert('인증 코드를 발송했습니다. 이메일을 확인하세요.');
							fnVerifyAuthCode(obj.authCode);
		                },
		    			error: function(jqXHR){
		                	alert('인증코드 발송이 실패하였습니다.');
						}
					})
				}
			).catch(
					function(code){
						if(code == 1000){
							$('#emailMsg').text('이메일 형식이 올바르지 않습니다.').addClass('dont').removeClass('ok');
							$('#authCode').prop('readonly', true);
						} else if(code == 2000){
							$('#emailMsg').text('이미 사용 중인 이메일입니다.').addClass('dont').removeClass('ok');
							$('#authCode').prop('readonly', true);
						}
					}
				)
			})
		}

	// 6. 메일 인증코드 검증
	let authCodePass = false;
	function fnVerifyAuthCode(authCode){
		$('#btnVerifyAuthCode').on('click', function(){
			if($('#authCode').val() == authCode){
				alert('인증되었습니다.');
				authCodePass = true;
			} else {
				aler('인증에 실패하였습니다.');
				authCodePass = false;
			}
		})
	}

	// 7. 인증코드 모두 대문자로 처리
	function fnToUpperCase(){
		$('#authCode').on('keyup', function(){
			$('#authCode').val($('#authCode').val().toUpperCase());
		})
	}

	// 8. 회원가입
	function fnSignIn(){
		$('#f').on('submit', function(event) {
			if(idPass == false) {
				alert('아이디를 확인하세요.')
				event.preventDefault();
				return false;
			} else if(pwPass == false || rePwPass == false) {
				alert('비밀번호를 확인하세요.')
				event.preventDefault();
				return false;
			} else if(authCode == false) {
				alert('인증 코드를 확인하세요.')
				event.preventDefault();
				return false;
			}
			return true;
		})
	}

</script>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

	<form id="f" action="${contextPath}/member/signIn" method="post">

		<input type="hidden" name="location" value="${agreements[0]}">
		<input type="hidden" name="promotion" value="${agreements[1]}">
	
		<label for="id">
			<b>아이디</b><br>
			<input type="text" name="id" id="id"><br>
			<span id="idMsg"></span>
		</label><br><br>

		<label for="pw">
			<b>비밀번호</b><br>
			<input type="password" name="pw" id="pw"><br>
			<span id="pwMsg"></span>
		</label><br><br>

		<label for="pwConfirm">
			<b>비밀번호 재확인</b><br>
			<input type="password" id="pwConfirm"><br>
			<span id="pwConfirmMsg"></span>
		</label><br><br>

		<label for="name">
			<b>이름</b><br>
			<input type="text" name="name" id="name"><br>
		</label><br><br>

		<label for="email">
			<b>이메일</b><br>
			<input type="text" name="email" id="email">
			<input type="button" value="인증번호 받기" id="btnGetAuthCode"><br>
			<span id="emailMsg"></span><br>
			<input type="text" name="authCode" id="authCode" placeholder="인증코드 입력">
			<input type="button" value="인증하기" id="btnVerifyAuthCode"><br><br>
		</label><br><br>

		<button>회원가입</button>
		<input type="button" value="취소하기" onclick="location.href='${contextPath}'">

	</form>

</body>
</html>