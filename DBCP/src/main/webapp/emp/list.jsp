<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<h1>전체사원 목록화면</h1>
	<div>전체 사원 수 : ${empCount}명</div>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empCount eq 0}">
				<tr>
					<td colspan="3">등록된 사원이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${empCount ne 0}">
				<c:forEach items="${list}" var="emp">
					<tr>
						<td>${emp.empNo}</td>
						<td><a href="/DBCP/detail.do?empNo=${emp.empNo}">${emp.name}</a></td>
						<td>${emp.dept}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>