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
		fnCheckAll();
		fnCheckOne();
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
					gender: $(':radio[name="gender"]:checked').val(),
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
					alert('예외코드[' + jqXHR.status + ']' + jqXHR.responseText);
				}
			})
		})
	}

	var page = 1;
	function fnList(){
		$.ajax({
			url: '${contextPath}/members/page/' + page,
			type: 'GET',
			dataType: 'json',
			success: function(obj){
				fnPrintMemberList(obj.members);
				fnPrintPaging(obj.p);
			}
		})
	}

	function fnPrintPaging(p){
		$('#paging').empty();

		var paging = '';

		//	⏪	: 이전 블록으로 이동
		if(page <= p.pagePerBlock){
			paging += '<div class="disable_link">⏪</div>';
		} else {
			paging += '<div class="enable_link" data-page="' + (p.beginPage + 1) + '">⏪</div>';
		}

		//	◀	: 이전 페이지로 이동
		if(page == 1){
			paging += '<div class="disable_link">◀</div>';
		} else {
			paging += '<div class="enable_link" data-page="' + (page - 1) + '">◀</div>';
		}

		//	1 2 3 4 5	: 페이지 번호
		for(let i = p.beginPage; i <= p.endPage; i++){
			if(i == page){
				paging += '<div class="disable_link now_page">' + i + '</div>';
			} else {
				paging += '<div class="enable_link" data-page="' + i + '">' + i + '</div>';
			}
		}

		//	▶	: 다음 페이지로 이동
		if(page == p.totalPage){
			paging += '<div class="disable_link">▶</div>';
		} else {
			paging += '<div class="enable_link" data-page="' + (page + 1) + '">▶</div>';
		}

		//	⏩	: 다음 블록으로 이동
		if(p.endPage == p.totalPage){
			paging += '<div class="disable_link">⏩</div>';
		} else {
			paging += '<div class="enable_link" data-page="' + (p.endPage + 1) + '">⏩</div>';
		}

		$('#paging').append(paging);
	}

	function fnPrintMemberList(members){
		$('#members').empty();
		$.each(members, function(i, member){
			var tr = '<tr>';
			tr += '<td><input type="checkbox" class="checkOne" value="' + member.memberNo + '"></td>';
			tr += '<td>' + member.id + '</td>';
			tr += '<td>' + member.name + '</td>';
			tr += '<td>' + member.gender + '</td>';
			tr += '<td>' + member.address + '</td>';
			tr += '<td><input type="button" value="조회" class="btnDetail" data-member_no="' + member.memberNo + '"></td>';
			tr += '</tr>';
			$('#members').append(tr);
		})
	}

	function fnPagingLink(){
		$(document).on('click', '.enable_link', function(){
			page = $(this).data('page');
			fnList();
		})
	}
	
	function fnCheckAll(){
		$('#checkAll').on('click', function(){
			$('.checkOne').prop('checked', $('#checkAll').prop('checked'));
		})
	}

	function fnCheckOne(){
		$(document).on('click', '.checkOne', function(){
			let checkCount = 0;
			for(let i = 0; i < $('.checkOne').length; i++){
				checkCount += $($('.checkOne')[i]).prop('checked')
			}
			$('#checkAll').prop('checked', checkCount == $('.checkOne').length);
		})
	}

</script>
<style>

	#paging {
		display : flex;
		justify-content: center;	
	}

	#paging div {
		width:  32px;
		height: 20px;
		text-align: center;
	}

	.disable_link {
		color: lightgray;
	}

	.enable_link {
		cursor: pointer;	
	}

	.now_page {
		border: 1px solid gray;
		color: limegreen;
		font-weight: 900;
	}

</style>
<title>Insert title here</title>
</head>
<body>

	<h1>회원관리</h1>

	<div>
		아이디	<input type="text" name="id" id="id"><br>
		이름	<input type="text" name="name" id="name"><br>
		성별
		<label for="male"><input type="radio" name="gender" value="M" id="male">남</label>
		<label for="female"><input type="radio" name="gender" value="F" id="female">여</label>
		<label for="none"><input type="radio" name="gender" value="NONE" id="none" checked></label><br>
		주소	<input type="text" name="address" id="address"><br><br>
		<input type="button" value="초기화" id="btnInit">
		<input type="button" value="등록" id="btnAdd">
		<input type="button" value="수정" id="btnChange">
	</div>

	<hr>

	<table border="1">
		<thead>
			<tr>
				<td><input type="checkbox" id="checkAll"></td>
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
				<td colspan="6">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>

	<br>

	<input type="button" value="선택삭제" id="btnRemove">
	
</body>
</html>