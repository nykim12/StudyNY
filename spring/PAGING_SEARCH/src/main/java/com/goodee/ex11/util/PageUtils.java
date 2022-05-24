package com.goodee.ex11.util;

public class PageUtils {

	private int totalRecord;		// DB에서 값 가져오기
	private int recordPerPage = 5;	// Util 클래스에서 지정
	private int totalPage;			// totalRecord, recordPage의 값을 통해 계산

	private int page;				// 파라미터로 받아옴
	private int beginRecord;		// page, recordPerPage의 값을 통해 계산
	private int endRecord;			// beginRecord, recordPerPage, totalPage의 값을 통해 계산

	private int pagePerBlock;
	private int beginPage;
	private int endPage;

	public void setPageEntity(int totalRecord, int page) {

		this.totalRecord = totalRecord;
		this.page = page;

		totalPage = totalRecord / recordPerPage;
		if (totalRecord % recordPerPage != 0) {
			totalPage++;
		}

		beginRecord = (page - 1) * recordPerPage + 1;

		endRecord = beginRecord + recordPerPage - 1;
		if (endRecord > totalRecord) {
			endRecord = totalRecord;
		}

	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBeginRecord() {
		return beginRecord;
	}

	public void setBeginRecord(int beginRecord) {
		this.beginRecord = beginRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}