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

	$(document).ready(function(){

//		<수정> location과 파라미터 noticeNo 활용
		$('#btnChangePage').on('click', function(){
			location.href='${contextPath}/notice/changePage?noticeNo=${notice.noticeNo}';
		})

//		<삭제> form id의 'f'의 submit 활용
		$('#btnRemove').on('click', function(){
			if(confirm('삭제하시겠습니까?')) {				
				$('#f').attr('action', '${contextPath}/notice/removeOne');
				$('#f').submit();
			}
		})

		$('#btnList').on('click', function(){
			location.href = '${contextPath}/notice/list';
		})
	})

</script>
</head>
<body>

	<form id="f">
		<!-- 삭제에서 활용 -->
		<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
		공지번호	${notice.noticeNo}<br>
		제목		${notice.title}<br>
		내용<br>
		<pre>${notice.content}</pre>
		조회수	${notice.hit}<br>
		작성일	${notice.created}<br>
		수정일	${notice.lastModified}<br>

		<input type="button" value="수정" id="btnChangePage">
		<input type="button" value="삭제" id="btnRemove">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>