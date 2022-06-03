package com.goodee.ex14.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.domain.FileAttach;

public interface GalleryService {

	public void findGalleries(HttpServletRequest request, Model model);

	public FileAttach findFileAttachByNo(long fileAttachNo);

	public void findGalleryByNo(HttpServletRequest request, Model model);
	
	public ResponseEntity<Resource> download(String userAgent, long fileAttachNo);

	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);

	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);

	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);

}