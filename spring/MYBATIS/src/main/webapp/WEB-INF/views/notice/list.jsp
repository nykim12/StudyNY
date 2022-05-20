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
	$(document).ready(function() {

	})
</script>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<td></td>
				<td>번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice">
				<tr>
					<td><input type="checkbox" name="noticeNoList" value="${notice.noticeNo}"></td>
					<td>${notice.noticeNo}</td>
					<td>${notice.title}</td>
					<td>${notice.created}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>