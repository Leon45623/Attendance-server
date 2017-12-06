package com.scb.po;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private int totalRows;		//记录总行数
	private int totalPages;     //查询分页的总数
	private int currentPage;	//当前页码
	private int pageSize;		//每一页规定的显示记录的个数
	private List data=new ArrayList<>();		//每一页包含的记录对象的集合
	public PageBean() {
		super();
	}
	public PageBean(int totalRows, int totalPages, int currentPage, int pageSize, List data) {
		super();
		this.totalRows = totalRows;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.data = data;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return this.totalRows%this.pageSize==0?this.totalRows/this.pageSize:this.totalRows/this.pageSize+1;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	
	
}
