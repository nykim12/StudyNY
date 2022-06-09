<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>로고</h1>

	<a href="${contextPath}/member/loginPage">로그인</a>
	<a href="${contextPath}/member/agreePage">회원가입 페이지</a>

	<a href="${contextPath}/member/signOut?memberNo=3">회원 탈퇴</a>

	<hr>

</body>
</html>