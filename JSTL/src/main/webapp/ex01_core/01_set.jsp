<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--

		변수 선언 태그

		1.	<c:set var="변수명" value="값" scope="영역"></c:set>
		2.	영역은 page, request, session, application 중 선택 (default : page)

	--%>

	<c:set var="name" value="김나연" scope="page" />
	<c:set var="age" value="30" scope="page" />

	<h3>이름 : ${name}</h3>
	<h3>나이 : ${age}</h3>

	<c:set var="tall" value="161.2" scope="page"></c:set>
	<c:set var="weight" value="45.3" scope="page"></c:set>	<!-- 이었으면 좋겠다.. -->

	체질량지수 BMI를 구하고, 건강상태 출력하기
	25 이상은 "관리요망" , 이외는 "정상"
	
	키 : m 단위 / 몸무게 : kg 단위
	BMI = 몸무게 / (키×키)
	
	<fmt:parseNumber var="BMI" value="${weight / ((tall*0.01)*(tall*0.01))}" integerOnly="true" />	<!-- weight / (tall*tall*10000) 으로도 계산 가능 -->
	<c:set var="health" value="${BMI >= 25 ? '관리요망' : '정상'}" scope="page"></c:set>
	
	<h3>체질량지수 : ${BMI}</h3>
	<h3>건강상태 : ${health}</h3>

</body>
</html>