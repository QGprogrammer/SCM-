package com.aowin.model;

import java.util.List;

/**
 * 页面分页
 * @author Peter
 *
 */
public class Page {
	private int pageSize;  //每页条数
	private int pageCount;  //页数
	private int currentPage; //当前第几页
	private int allcount;  //总记录数
	private List<?> data;	//记录list
	public Page() {}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		//总页数
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAllcount() {
		return allcount;
	}
	public void setAllcount(int allcount) {
		this.allcount = allcount;
		setPageCount(allcount%pageSize==0?allcount/pageSize : allcount/pageSize + 1);
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
}
