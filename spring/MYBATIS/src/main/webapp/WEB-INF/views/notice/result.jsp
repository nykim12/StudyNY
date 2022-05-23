<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script>

//	${res} > 0		: ${res} 없이 처리될 경우 if ( > 0) 컴파일 오류 발생으로 화면 중지됨
//	'${res}' == '1'	: ${res} 없이 처리될 경우 if ( == '1') 컴파일 오류없이 실행
	if('${kind}' == 'insert') {
		if('${res}' == '1'){
			alert('공지사항이 등록되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('공지사항이 등록되지 않았습니다.');
			history.back();
		}
	}

	if('${kind}' == 'update') {
		if('${res}' == '1') {
			alert('공지사항이 수정 완료되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('공지사항 수정이 실패하였습니다.');
			history.back();
		}
	}

	if('${kind}' == 'deleteOne') {
		if('${res}' == '1') {
			alert('삭제 완료되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('삭제 실패하였습니다.');
			history.back();
		}
	}

	if('${kind}' == 'deleteList') {
		if('${res}' == '1') {
			alert('전체 삭제 완료되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('삭제 실패하였습니다.');
			history.back();
		}
	}

</script>