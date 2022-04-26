<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
	<a href="/BoardProject/insertPage.do">작성하러 가기</a>
		<hr>
		<table border = "1">
			<thead>
				<tr>
					<td>게시글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="free">
					<tr>
						<td>${free.freeNo}</td>
						<td><a href="/BoardProject/detail.do?freeNo=${free.freeNo}">${free.title}</td>
						<td>${free.writer}</td>
						<td>${free.hit}</td>
						<td><a href="/BoardProject/remove.do">X</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>