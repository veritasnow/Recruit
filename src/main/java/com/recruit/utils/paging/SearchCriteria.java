package com.recruit.utils.paging;

public class SearchCriteria extends Criteria{

	private String searchType = "all";  // 검색 타입
	private String keyword    = ""; // 검색 키워드
	private String url;

	
	public SearchCriteria(String searchType, String keyword, String url) {
		setSearchType(searchType);
		setKeyword(keyword);
		setUrl(url);
	}
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		String result = "searchType :" +searchType +"/" + "keyword :" + keyword + "/" + "url : " + url;		
		return result;
	}

}