<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(document).ready(function(){

		$('#f').on('submit', function(event){

			if($('#title').val() == '' || $('#author').val() == ''){
				alert('����� ���ڴ� �ʼ��Դϴ�.');
				event.preventDefault();  // return;
			}

			var regPubDate = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
			if( regPubDate.test($('#pubDate').val()) == false ) {
				alert('�������� YYYY-MM-DD �������� �Է��ؾ� �մϴ�.');
				event.preventDefault();
			}

		})

		$('#btnList').on('click', function(event){
			location.href='${contextPath}/book/list';
		})
		
	})

</script>
</head>
<body>

	<form id="f" action="${contextPath}/book/save" method="post">
		<input type="text" name="title" id="title" placeholder="����"/><br>
		<input type="text" name="author" id="author" placeholder="����"/><br>
		<input type="text" name="price" id="price" placeholder="����"/><br>
		<input type="text" name="pubDate" id="pubDate" placeholder="������(YYYY-MM-DD)"/><br>
		<button>���</button>
		<input type="reset" value="�ٽ��ۼ�">
		<input type="button" value="���" id="btnList">
	</form>

</body>
</html>