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

//	갤러리 상세
	public int selectGalleryCount();
	public List<FileAttach> selectGalleryList(Map<String, Object> map);
	public FileAttach selectFileAttachByNo(long fileAttachNo);
	public Gallery selectGalleryByNo(long galleryNo);
	public List<FileAttach> selectFileAttachListInTheGallery(long galleryNo);

//	갤러리 삽입
	public int updateGalleryHit(long galleryNo);	
	public int updateDownloadCnt(long galleryNo);

//	갤러리 삭제
	public int deleteGallery(long galleryNo);

	public int updateGallery(Gallery gallery);

	public int deleteFileAttach(long fileAttachNo);

//	어제 저장된 첨부 파일 리스트
	public List<FileAttach> selectFileAttachListAtYesterday();

}