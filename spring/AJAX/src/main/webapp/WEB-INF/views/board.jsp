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
		$('#btn4').on('click',fnAjax4);
	})
	
	function fnAjax1(){
		$('#result').empty();
		$.ajax({
			url: '${contextPath}/board/detail1',
			type: 'get',
			data: 'title=' + $('#title').val() + '&hit=' + $('#hit').val(),
			dataType: 'json',
			success: function(obj){
				$('<div>')
				.append($('<h3>').text('제목: ' + obj.title))
				.append($('<h3>').text('조회수: ' + obj.hit))
				.appendTo($('#result'))
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
	
	function fnAjax2(){
		$('#result').empty();
		
		$.ajax({
			url: '${contextPath}/board/detail2',
			type: 'get',
			data: $('#b').serialize(),
			dataType: 'json',
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.title))
				.append($('<li>').text(obj.hit))
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
			url: '${contextPath}/board/detail4',
			type: 'post',
			data: JSON.stringify({
				'title': $('#title').val(),
				'hit': $('#hit').val()
			}),
			contentType: 'application/json',
			dataType: 'json',
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.title))
				.append($('<li>').text(obj.hit))
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
			url: '${contextPath}/board/detail4',
			type: 'post',
			data: JSON.stringify({
				'title': $('#title').val(),
				'hit': $('#hit').val()
			}),
			contentType: 'application/json',
			dataType: 'json',
			success: function(obj){
				$('<ul>')
				.append($('<li>').text(obj.title))
				.append($('<li>').text(obj.hit))
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
	<form id="b">
		<input type="text" name="title" id="title" placeholder="title"><br>
		<input type="text" name="hit" id="hit" placeholder="hit">
		<input type="button" value="전송1" id="btn1">
		<input type="button" value="전송2" id="btn2">
		<input type="button" value="전송3" id="btn3">
		<input type="button" value="전송4" id="btn4">
	</form>
	<hr>
	<div id="result"></div>
</body>
</html>