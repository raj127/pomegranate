package com.darkmi.solr.test;

public class Page {
	private int currentPage;
	private int perPageSize;
	private int counts;
	
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPerPageSize() {
		return perPageSize;
	}
	public void setPerPageSize(int perPageSize) {
		this.perPageSize = perPageSize;
	}

}
