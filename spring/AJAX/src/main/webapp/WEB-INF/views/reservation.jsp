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
		$('#btn1').on('click',fnAjax1);
		$('#btn2').on('click',fnAjax2);
		$('#btn3').on('click',fnAjax3);
	})

	function fnAjax1(){
		$.ajax({
			url: '${contextPath}/reservation/detail1',
			type: 'get',
			data: 'no='+$('#no').val(),
			dataType: 'json',
			success: function(reservation){
				$('#result').empty();
				$('<ul>')
				.append($('<li>').text(reservation.no))
				.append($('<li>').text(reservation.name))
				.appendTo('#result');
			},
			error: function(jqXHR){
				if(jqXHR.status == 500){
					alert('잘못된 요청입니다.');
				}
				$('#result').empty();
//				$('#result').text(jqXHR.responseText);
			}
		})
	}	// fnAjax1


	function fnAjax2(){
		$.ajax({
			url: '${contextPath}/reservation/detail2',
			type: 'get',
			data: 'no='+$('#no').val(),
			dataType: 'json',
			success: function(reservation){
				$('#result').empty();
				$('<ul>')
				.append($('<li>').text((reservation.no) + ", " + (reservation.name)))
				.appendTo('#result');
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){
					alert('잘못된 요청입니다.');
				}
				$('#result').empty();
//				$('#result').text(jqXHR.responseText);
			}
		})
	}

	function fnAjax3(){
		$.ajax({
			/* 요청 */
			url: '${contextPath}/reservation/detail3',
			type: 'post',
			data: JSON.stringify({'no': $('#no').val()}),
			contentType: 'application/json',
			/* 응답 */
			dataType: 'json',
			success: function(reservation){
				$('#result').empty();
				$('<ul>')
				.append($('<li>').text(reservation.no))
				.append($('<li>').text(reservation.name))
				.appendTo('#result');
			},
			error: function(jqXHR){
				if(jqXHR.status == 500){
					alert('저장할 수 없는 데이터입니다.');
				}
				$('#result').empty();
				$('#result').text(jqXHR.responseText);
			}
		})
	}  // fnAjax3

</script>
</head>
<body>

	<input type="text" name="no" id="no" placeholder="예약번호">
	<input type="button" id="btn1" value="조회1">
	<input type="button" id="btn2" value="조회2">
	<input type="button" id="btn3" value="조회3">

	<hr>

	<div id="result"></div>

	<hr>

	<!--
	<img> 태그는 절대 경로의 이미지 표시 불가
	절대 경로의 이미지는 ajax로 이미지를 읽어 둔다.
	-->
	<img alt="pika.png" src="${contextPath}/reservation/image">

</body>
</html>