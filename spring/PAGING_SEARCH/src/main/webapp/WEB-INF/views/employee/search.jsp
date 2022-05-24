<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>


	$(function(){
		fnAreaChoice();
	})


	function fnAreaChoice(){

		//	초기 : 모두 숨김
		$('#equalArea, #rangeArea').css('display', 'none');

		//	선택
		$('#column').on('change', function(){
			if($(this).val() == '') {
				$('#equalArea, #rangeArea').css('display', 'none');
			} else if( $(this).val() == 'EMPLOYEE_ID' || $(this).val() == 'FIRST_NAME' ) {
				$('#equalArea').css('display', 'inline');
				$('#rangeArea').css('display', 'none');
			} else {
				$('#equalArea').css('display', 'none');
				$('#rangeArea').css('display', 'inline');				
			}
		})

	}



</script>
</head>
<body>

	<h1>사원 검색</h1>

	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="EMPLOYEE_ID">사원번호</option>
			<option value="FIRST_NAME">이름</option>
			<option value="HIRE_DATE">고용일</option>
			<option value="SALARY">연봉</option>
		</select>
		<span id="equalArea">
			<input type="text" name="query" id="query" list="autoComplete">	<!-- autoComplete : 자동완성 -->
			<datalist id="autoComplete"></datalist>
		</span>
		<span id="rangeArea">
			<input type="text" name="begin" id="bigin">
			~
			<input type="text" name="end" id="end">
		</span>
		<input type="button" value="검색" id="btnSearch">
		<input type="button" value="전체사원조회" id="btnSearchAll">
	</form>

	<br><hr><br>

	<%@ include file="list.jsp" %>

</body>
</html>