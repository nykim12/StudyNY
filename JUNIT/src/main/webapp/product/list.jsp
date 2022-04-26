<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="/JUNIT/addPage.do">제품 등록하기</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>제품번호</td>
				<td>제품이름</td>
				<td>제품가격</td>
				<td>이미지</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4">등록된 상품이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="product">
					<tr>
						<td>${product.product_no}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td><c:if test="${not empty product.image}"></td>
								<i class-"fa-solidfa-paperclip"></i>
							</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>