<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
		fnInit();
		fnFileCheck();
		fnAdd();
		fnAttached();
	})

	function fnInit(){
		
	}

	function fnFileCheck(){
		$('#files').on('change', function(){
			// 첨부 규칙
			var regExt = /(.*)\.(jpg|png|gif|JPG|PNG|GIF)$/;
			var maxSize = 1024 * 1024 * 10;		// 하나당 최대 크기
			// 첨부 가져오기
			var files = $(this)[0].files;
			// 각 첨부의 순회
			for(let i = 0; i < files.length; i++) {
				// 확장자 체크
				if( regExt.test(files[i].name) == false ){
					alert('이미지만 첨부 가능합니다.');
					$(this).val('');	// 첨부된 파일이 모두 없어짐
					return;
				}
				// 크기 체크
				if(files[i].size > maxSize){
					alert('10MB 이하의 파일만 첨부 가능합니다.');
					$(this).val('');
					return;
				}
			}
		})
	}

	function fnAdd(){
		
	}

	function fnAttached(){
	
	}

</script>
</head>
<body>

	<h1>갤러리 관리</h1>

	<div>
		작성자	<input type="text" name="writer" id="writer"><br>
		제목	<input type="text" name="title" id="title"><br>
		내용	<input type="text" name="content" id="content"><br>
		첨부	<input type="file" name="files" id="files" multiple="multiple"><br>
		<input type="button" value="등록" id="btnAdd">
	</div>

	<div>
		<h3>첨부 파일 확인</h3>
		<div id="attached"></div>	
	</div>

</body>
</html>