<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		$('.reply_link').on('click', function(){
			$(this).parent().parent().next().toggleClass('reply_form');
		})
	})
</script>
<style>
	.reply_form {
		display: none;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="">새글작성</a>

	<hr>

	<table>
		<caption>${totalRecord}개 게시글</caption>
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty freeBoards}">
				<tr>
					<td colspan="5">첫 게시글을 작성해 주세요.</td>
				</tr>
			</c:if>
			<c:if test="${not empty freeBoards}">
				<c:forEach var="fb" items="${freeBoards}">
					<c:if test="${fb.state == -1}">
						<tr>
							<td colspan="5">삭제된 게시글입니다.</td>
						</tr>
					</c:if>
					<c:if test="${fb.state == 1}">
						<tr>
							<td>${fb.rowNum}</td>
							<td>${fb.writer}</td>
							<td>
								<!-- Depth만큼 들여쓰기(Depth 1단계 : +2 space) -->
								<c:forEach begin="1" end="${fb.depth}" step="1">&nbsp;&nbsp;</c:forEach>
								<!-- 댓글은 re 표시 -->
								<c:if test="${fb.depth>0}">
									<i class="fa-brands fa-replyd"></i>
								</c:if>
								<!-- 내용 길이 표시 -->
								<c:if test="${fb.content.length() gt 20}">
									${fb.content.substring(0,20)}
								</c:if>
								<c:if test="${fb.content.length() le 20}">
									${fb.content}
								</c:if>
								<!-- 답글 등록 : if가 있으면 1단 댓글만 허용 / if가 없으면 다단 댓글 허용 -->
								<c:if test="${fb.depth eq 0}">
									<a class="reply_link">답글</a>
								</c:if>
							</td>
							<td>${fb.created}</td>
							<td>삭제버튼보여주기</td>
						</tr>
						<!-- class 속성값 reply_form을 가지고 있으면 안 보임 -->
						<tr class="reply_form">
							<td colspan="5">
								<form action="${contextPath}/freeBoard/saveReply" method="post">
									<input type="text" name="writer" placeholder="작성자" size="4">
									<input type="text" name="content" placeholder="내용" size="40">
									<!-- 원글의 Depth, GroupNo, GroupOrd -->
									<input type="hidden" name="depth" value="${fb.depth}">
									<input type="hidden" name="groupNo" value="${fb.groupNo}">
									<input type="hidden" name="groupOrd" value="${fb.groupOrd}">
									<button>등록</button>
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>