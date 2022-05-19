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
		$('.book_title').on('click', function(){
			location.href='${contextPath}/book/detail?book_no=' + $(this).data('book_no');	//	data-book_no �Ӽ� ��
//			location.href='${contextPath}/book/detail?book_no=' + $(this).prev().text();	//	<td>${book.book_no}</td>
		})
	})
	
	$('#btnSave').on('click', function(){
		location.href='${contextPath}/book/savePage';
	})
	
</script>
</head>
<body>

	<input type="button" value="���" id="btnSave">

	<hr>

	<table border="1">
		<thead>
			<tr>
				<td>��ȣ</td>
				<td>����</td>
				<td>����</td>
				<td>������</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${books}">
				<tr>
				<td>{book.book_no}</td>
				<td class="book_title" data-book_no="${book.book_no}">{book.title}</td>
				<td>{book.autor}</td>
				<td>{book.pubDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>