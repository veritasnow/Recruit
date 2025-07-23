package com.recruit.utils.paging;

public class Criteria {

	/*
	 * int page           : 현재 페이지 번호
	   int selectPage     : 선택한 페이지
	 * int perPageNum     : 한 페이지당 보여줄 게시글의 갯수
	 * int getPageStart() : 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
	 */
	
	private int page;
	private int selectPage;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.selectPage = 1;
		this.perPageNum = 20;
	}
	
	public int getPageStart() {
		return (this.page - 1)*perPageNum;		
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;			
		} else {
			this.page = page;
		}		
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}

	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;

		if(pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}		
	}
	
}