package com.goodee.ex11.util;

public class PageUtils {

	/***************************************************************************

		int totalRecord
		 - 전체 레코드 수
		 - DB에서 값을 가져온다.
		
		int recordPerPage
		 - 한 페이지에 보여줄 레코드 갯수
		 - PageUtils 클래스에서 숫자를 직접 지정

		int totalPage
		 - 전체 페이지 수
		 - totalRecord, recordPage의 값을 통해 계산


		int page
		 - 페이지 번호
		 - 파라미터로 가져온다.

		int beginRecord
		 - 페이지 번호(page) 내 레코드 시작 번호
		 - page, recordPerPage의 값을 통해 계산

		int endRecord
		 - 페이지 번호(page) 내 레코드 끝 번호
		 - beginRecord, recordPerPage, totalPage의 값을 통해 계산


		int pagePerBlock
		 - 화면에 보여줄 페이지 번호 총 갯수
		 - PageUtils 클래스에서 숫자를 직접 지정

		int beginPage
		 - 화면에 보여지는 페이지 시작 번호
		 - page, pagePerBlock의 값을 통해 계산

		int endPage	
		 - 화면에 보여지는 페이지 끝 번호
		 - beginPage, pagePerBlack, totalPage의 값을 통해 계산

	*****************************************************************************/	
	
	
	private int totalRecord;
	private int recordPerPage = 5;
	private int totalPage;

	private int page;
	private int beginRecord;
	private int endRecord;

	private int pagePerBlock = 5;
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
		
		beginPage = (pagePerBlock * (page - 1) / pagePerBlock) + 1;
		
		endPage = beginPage + pagePerBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

	}

//	매개변수 1개
//	path : "employee/list", "/board/list" 등이 각 ServiceImpl에서 전달됨
	public String getPaging(String path) {

		StringBuilder sb = new StringBuilder();

		// 1페이지로 이동, 1페이지는 <a> 태그가 없다.
		if(page == 1) {
			sb.append("prev");
		} else {
			sb.append("<a href=\"" + path + "?page=1\">1</a>");
		}


		// 이전 블록으로 이동, 1블록은 <a> 태그가 없다.
		if(page <= pagePerBlock) {
			sb.append("prevBlock");
		} else {
			sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) + "\">prevBlock</a>");
		}


		// 이전 페이지 (prev), 1페이지는 <a> 태그가 없다.
		if(page == 1) {
			sb.append("prev");
		} else {
			sb.append("<a href=\"" + path + "?page=" + (page - 1) + "\">prev</a>");
		}


		// 페이지 번호 (1 2 3 4 5), 현재 페이지는 <a> 태그가 없다.
		for(int p = beginPage; p <= endPage; p++) {
			if(p == page) {
				sb.append(p);
			} else {
				sb.append("<a href=\"" + path + "?page=" + p + "\">" + p + "</a>");
			}
		}


		// 다음 페이지 (next), 마지막 페이지는 <a> 태그가 없다.
		if(page == totalPage) {
			sb.append("next");
		} else {
			sb.append("<a href=\"" + path + "?page=" + (page + 1) + "\">next</a>");
		}


		// 다음 블록으로 이동, 마지막 블록에는 <a> 태그가 없다.
		if(endPage == totalPage) {
			sb.append("nextBlock");
		} else {
			sb.append("<a href=\"" + path + "?page=" + (endPage + 1) + "\">nextBlock</a>");
		}


		// 마지막페이지로 이동, 마지막 페이지는 <a> 태그가 없다.
		if(page == totalPage) {
			sb.append("next");
		} else {
			sb.append("<a href=\"" + path + "?page=" + totalPage + "\">" + totalPage + "</a>");
		}
				
		return sb.toString();

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