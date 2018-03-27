package com.coupon.dto;

import java.util.List;

public class PageResponse {

	private int pageNum;
	private int pageCount;
	
	private long totalRow;
	private int totalPage;
	
	private List<CouponEntity> list;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<CouponEntity> getList() {
		return list;
	}

	public void setList(List<CouponEntity> list) {
		this.list = list;
	}
}
