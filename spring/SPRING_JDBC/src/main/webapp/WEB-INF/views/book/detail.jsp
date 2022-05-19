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
		$('#f').on('submit', functon(ev){

			if($('#title').val() == '' || $('#author').val() == ''){
				alert('����� ���ڴ� �ʼ��Դϴ�.');
				event.preventDefault();	//	== return;
			}

			//	���� å ����		����ڰ� �Է��� å ����
			//	${BOOK.TITLE}	('#TITLE').BAL()

		})

		$('#btnRemove').on('click', function(ev){
			if(confirm('"${book.title}" å�� �����ұ��?')){
				location.href='${contextPath}/book/remove?book_no=' + ${book.book_no};
			}
			
		})		

		$('#btnList').on('click', function(ev){
			location.href = '${contextPath}/book/list';
		})

	})

</script>
</head>
<body>

	<form id="f" action="${contextPath}/book/change" method="post">
		<input type="text" name="title" id="title" value="${book.title}"/><br>
		<input type="text" name="author" id="author" value="${book.author}"/><br>
		<input type="text" name="price" id="price" value="${book.price}"/><br>
		������	${book.pubDate}	<br>
		�����	${book.pubDate}	<br>
		<input type="hidden" name="book_no" value="${book.book_no}" /><br>
		<button>����</button>
		<input type="reset" value="�ٽ��ۼ�">
		<input type="button" value="����" id="btnRemove">
		<input type="button" value="���" id="btnList">
	</form>

</body>
</html>