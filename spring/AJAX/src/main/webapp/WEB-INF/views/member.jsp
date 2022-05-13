<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.js"></script>
<script>
	$(document).ready(function(){
		$('#btn1').on('click',function(){
			fnAjax1();
		})

		$('#btn2').on('click',function(){
			fnAjax2();
		})

		$('#btn3').on('click',fnAjax3)
		$('#btn4').on('click',fnAjax4)
		$('#btn5').on('click',fnAjax5)
	})
	
	//	요청 데이터 : 파라미터
	//	응답 데이터 : 텍스트
	function fnAjax1(){
		
		$('#result').empty();
		
		$.ajax({
			/* 요청에 관한 프로퍼티 : url, type, data, contentType */
			url: '${contextPath}/member/detail1',	//	요청 URL(매핑), MemberController에서 매핑 찾기
			type: 'get',
			data: 'id=' + $('#id').val() + '&pw=' + $('#pw').val(),
			/* 응답 데이터에 관한 프로퍼티 : dataType, success, error */
			dataType: 'text',		//	응답 데이터 타입 text
			success: function(res){	//	응답 데이터는 res에 전달
				$('#result').text(res);
			},
			error: function(JqXHR){
				$('#result'.text(jqXHR.status + ' : ' + jqXHR.responseText));
			}
		})
	}

	//	요청 데이터 : 파라미터
	//	응답 데이터 : JSON
	//	{"id":"admin", "pw":"123456"}
	function fnAjax2(){
		$('#result').empty();
		$.ajax({
			/* 요청 */
			url: '${contextPath}/member/detail2',
			type: 'get',
			data: $('#f').serialize(),
			//	<input name="id">에 입력된 값은 파라미터 id로 전달
			//	<input name="pw">에 입력된 값은 파라미터 pw로 전달
			dataType: 'json',	//	응답 데이터 타입 json
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.id))
				.append($('<li>').text(obj.pw))
				.appendTo($('#result'));
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
	
	function fnAjax3(){
		$('#result').empty();
		
		$.ajax({
			/* 요청 */
			url: '${contextPath}/member/detail3',
			type: 'get',
			data: $('#f').serialize(),
			/* 응답 */
			dataType: 'json',
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.id))
				.append($('<li>').text(obj.pw))
				.appendTo($('#result'));
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
	
	function fnAjax4(){
		$('#result').empty();
		
		$.ajax({
			/* 요청 */
			url: '${contextPath}/member/detail4',
			/*	JSON 데이터를 서버로 보내고자 할 때에는 JSON 데이터를 주소 창에 붙여 보내지 못함
				JSON 데이터를 본문에 포함시켜 보내는 POST 방식으로 보내야 함	*/
			type: 'post',

			/*	JSON 데이터를 만들어서 보낼 때에는 JSON 데이터를 문자열 형식으로 만들어 보냄	*/
			data: JSON.stringify({
				'id': $('#id').val(),
				'pw': $('#pw').val()
			}),

			/*	JSON 데이터를 만들어 보낼 때에는 보내는 데이터의 타입을 작성해 줌
				contentType 이라는 속성으로 작업, 요청 데이터 타입을 확인하는 것은 JAVA 측이기 때문에
				JAVA가 사용하는 JSON의 타입인 'application/json' 으로 작성해야 함
			*/
			contentType: 'application/json',
			
			/* 응답 */
			dataType: 'json',
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.id))
				.append($('<li>').text(obj.pw))
				.appendTo($('#result'));
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
	
	function fnAjax5(){
		$.ajax({
			url: '${contextPath}/member/detail5',
			type: 'post',
			data: JSON.stringify({
				'id': $('#id').val(),
				'pw': $('#pw').val()
			}),
			contentType: 'application/json',
			dataType: 'json',
			success: function(obj){				
				$('<ul>')
				.append($('<li>').text(obj.id)) 
				.append($('<li>').text(obj.pw))
				.appendTo($('#result'));
				
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
</script>
</head>
<body>
	<form id="f">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<input type="text" name="pw" id="pw" placeholder="PASSWORD"><br>
		<input type="button" value="전송1" id="btn1">
		<input type="button" value="전송2" id="btn2">
		<input type="button" value="전송3" id="btn3">
		<input type="button" value="전송4" id="btn4">
		<input type="button" value="전송5" id="btn5">
	</form>
	<hr>
	<div id="result"></div>
</body>
</html>