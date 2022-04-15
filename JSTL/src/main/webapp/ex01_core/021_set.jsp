<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		age는 문자열 "30"을 저장
		request.setAttribute("age", "30");
	--%>
	<c:set var="age" value="30" scope="request" />

	<%-- request를 전달하는 forward --%>
	<% request.getRequestDispatcher("022_set.jsp").forward(request, response); %>

</body>
</html>