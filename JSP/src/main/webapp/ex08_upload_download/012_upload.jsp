<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		//	1. 업로드 경로
		String realPath = application.getRealPath("storage");	//	루트에 생성되는 storage 디렉토리

		//	2. 업로드 경로(디렉토리) 생성
		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}

		//	3. 업로드 진행
		//	cos.jar 라이브러리가 제공하는 MultipartRequest mr 인스턴스를 생성하면 업로드가 진행됨
		//	MultipartRequest mr = new MultipartRequest(기존 request, 업로드 경로, 사이즈 최대 크기, 인코딩, 파일명 중복)
		MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());

		//	4. 각종 정보 확인
		String uploader = mr.getParameter("uploader");
		String originalName = mr.getOriginalFileName("filename");
		String filesystemName = mr.getFilesystemName("filename");
		File file = mr.getFile("filename");
		long size = file.length();	// 바이트 단위
		long lastModified = file.lastModified();	// 타임스탬프
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	%>
	
	<h3>업로더 : <%=uploader%></h3>
	<h3>기존 파일명 : <%=originalName%></h3>
	<h3>저장된 파일명 : <%=filesystemName%></h3>
	<h3>파일 크기 : <%=new DecimalFormat("#,##0").format(size/1024) %>KB</h3>
	<h3>최종 수정 : <%=dateFormat.format(new Date(lastModified)) %></h3>
	<h3>파일 경로 : <%=dir%></h3>
	
	<div>
		<img src="../storage/<%=filesystemName%>" width="500px">
	</div>
	
	
	

</body>
</html>