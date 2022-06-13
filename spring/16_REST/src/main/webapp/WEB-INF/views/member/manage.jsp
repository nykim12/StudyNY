<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
		fnInit();
		fnAdd();
		fnList();
	})

	function fnInit(){
		$('#btnInit').on('click', function(){
			$('#id').val('').prop('readOnly', false);
			$('#name').val('');
			$(':radio[name="gender"]').prop('checked', false);
			$('#address').val('');
		})
	}

	function fnAdd(){
		$('#btnAdd').on('click', function(){
			// 추가할 회원 정보를 JSON으로 만듦
			let member = JSON.stringify(
				{
					id: $('#id').val(),
					name: $('#name').val(),
					//gender: $(':radio[name="gender"]:checked').val(),
					address: $('#address').val()
				}
			)
			$.ajax({
				// 요청
				url: '${contextPath}/members',
				type: 'POST',
				data: member,
				contentType: 'application/json',
				// 응답
				dataType: 'json',
				success: function(obj){
					if(obj.res > 0){
						alert('회원 등록이 완료되었습니다.')
						fnInit();
						fnList();
					} else {
						alert('회원 등록에 실패하였습니다.')
					}
				},
				error: function(jqXHR){
					if(jqXHR.status == 501){	// 아이디 중복 코드
						alert(jqXHR.responseText);
					} else if(jqXHR.status == 502) {	// 필수 정보 누락 코드
						alert(jqXHR.responseText);
					}
				}
			})
		})
	}

	function fnList(){
		let members = '';
		members += '<tr><td><input type="checkbox" class="CheckOnes" value="1"></td></tr>';
		members += '<tr><td><input type="checkbox" class="CheckOnes" value="2"></td></tr>';
		members += '<tr><td><input type="checkbox" class="CheckOnes" value="3"></td></tr>';
		members += '<tr><td><input type="checkbox" class="CheckOnes" value="4"></td></tr>';
		members += '<tr><td><input type="checkbox" class="CheckOnes" value="5"></td></tr>';
		$('#members').empty();
		$('#members').html(members);
	}

</script>
<title>Insert title here</title>
</head>
<body>

	<h1>회원관리</h1>

	<div>
		아이디	<input type="text" name="id" id="id"><br>
		이름	<input type="text" name="name" id="name"><br>
		<!-- 성별
		<label for="male"><input type="radio" name="gender" value="M" id="male">남</label>
		<label for="female"><input type="radio" name="gender" value="F" id="female">여</label><br> -->
		주소	<input type="text" name="address" id="address"><br><br>
		<input type="button" value="초기화" id="btnInit">
		<input type="button" value="등록" id="btnAdd">
		<input type="button" value="수정" id="btnChange">
	</div>

	<hr>

	<table border="1">
		<caption id="paging"></caption>
		<thead>
			<tr>
				<td><input type="checkbox"></td>
				<td>아이디</td>
				<td>이름</td>
				<td>성별</td>
				<td>주소</td>
				<td>  </td>
			</tr>
		</thead>
		<tbody id="members"></tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<input type="button" value="선택삭제" id="btnRemove">
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>