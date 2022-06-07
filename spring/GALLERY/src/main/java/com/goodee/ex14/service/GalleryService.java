package com.goodee.ex14.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GalleryService {

	public void findGalleries(HttpServletRequest request, Model model);

//	갤러리 상세
	public void findGalleryByNo(HttpServletRequest request, Model model);
	public ResponseEntity<byte[]> display(Long fileAttachNo, String type);
	public ResponseEntity<Resource> download(String userAgent, long fileAttachNo);

	public void save(MultipartHttpServletRequest multipartrequest, HttpServletResponse response);

	public void change(MultipartHttpServletRequest multipartrequest, HttpServletResponse response);

	public void removeGallery(HttpServletRequest request, HttpServletResponse response);

	public void removeFileAttach(long fileAttachNo);
	
}