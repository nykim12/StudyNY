<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	
	// 상세 버튼의 이전 형제로 학번을 저장해 두고 사용한다.
	$('#detail').click(function(){
	    location.href='/BoardProject/detail.do?freeNo=' + $(this).prev().val();
	});
});

</script>
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
						<td onclick="location.href='/BoardProject/remove.do'" value="${free.freeNo}">${free.title}</td>
						<td>${free.writer}</td>
						<td>${free.hit}</td>
						<td>X</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>