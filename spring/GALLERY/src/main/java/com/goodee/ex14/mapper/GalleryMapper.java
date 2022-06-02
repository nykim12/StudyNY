package com.goodee.ex14.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex14.domain.FileAttach;
import com.goodee.ex14.domain.Gallery;

@Mapper
public interface GalleryMapper {

	public int insertGallery(Gallery gallery);
	public int insertFile(FileAttach fileAttach);
	
	public int selectGalleryCount();
	public List<FileAttach> selectGalleryList(Map<String, Object> map);
	public FileAttach selectFileAttachByNo(long fileAttachNo);
	
}