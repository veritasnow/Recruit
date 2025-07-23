package com.recruit.utils.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	/*
	- 총 게시글 수를 셋팅할때 calcData() 메서드를 호출하여 페이징 관련 버튼 계산을 한다.
	- 페이징의 버튼들을 생성하는 계산식을 만들었다. 끝 페이지 번호, 시작 페이지 번호, 이전, 다음 버튼들을 구한다.
	- Criteria cri.getPage() : 현재 페이지 번호
    - Criteria cri.getPerPageNum() : 한 페이지당 보여줄 게시글의 갯수
    - int totalCount : 총 게시글 수
	 */
	
	private Criteria cri;
	private SearchCriteria sCri;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 5;

	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public SearchCriteria getsCri() {
		return sCri;
	}
	public void setsCri(SearchCriteria sCri) {
		this.sCri = sCri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);		
		startPage = (endPage - displayPageNum) + 1;
		
		if(startPage <= 0) {
			startPage = 1;
		}

		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		if(endPage > tempEndPage) {
			if(tempEndPage == 0) {
				endPage = 1;
			} else {
				endPage = tempEndPage;				
			}
		}
		
		prev = startPage == 1 ? false : true;
		next = (endPage * cri.getPerPageNum()) < totalCount ? true : false;
	}
	
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	

	
	//예시 : /board/boardDetail${pageMaker.makeQueryPage(page)}
	
	
	/*	  
	2개의 메소드를 생성했다. idx가 필요한 uri도 있어서 추가해줬는데,
	둘 중에서 첫번째의 메소드만 사용해도 된다.
	그렇게 한다면, idx도 필요한 uri는
	/board/boardDetail${pageMaker.makeQueryPage(page)}&idx=23
	 이렇게 뒤에 문자열을 추가해서 보내주면 된다.
	 나는 idx 포함된 uri 주소를 만들었기 때문에 idx가 필요없으면 page 정보만,
	 idx가 필요하면 idx, page 정보를 넘겨주면 된다.

	 https://to-dy.tistory.com/99
	 */
	
	public String page(int page, String searchType, String keyword) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("selectPage", page)
                .queryParam("perPageNum", cri.getPerPageNum())
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build();
        return uri.toUriString();
    }
    
	
	/*
    public String page(int idx, int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("idx", idx)
                .queryParam("page", page)
                .queryParam("perPageNum", cri.getPerPageNum())
                .build();
        return uri.toUriString();
    }
	
    public String search(int page){        
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .queryParam("page", page)
            .queryParam("perPageNum", cri.getPerPageNum())
            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
            .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
            .build();           
        return uriComponents.toUriString();
    }
    */
	
}