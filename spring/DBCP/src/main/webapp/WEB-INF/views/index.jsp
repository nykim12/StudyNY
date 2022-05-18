<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	$(document).ready(()=>{
		$('#btnRemove').on('click', ()=>{
			if(confirm('삭제할까요?')){
				location.href='${contextPath}/board/remove?board_no=${board_no}'
			}
		})
	})
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/board/list">게시판관리</a>
</body>
</html>