package com.calm.dao.helper.entity;

import java.io.Serializable;
import java.util.List;

public interface Paging<T extends Serializable> extends Serializable{
	List<? extends T> getData();

	void setData(List<? extends T> data);

	int getCurrentPage();

	void setCurrentPage(int currentPage);

	int getTotalPage();

	void setTotalPage(int totalPage);

	int getPageSize();

	void setPageSize(int pageSize);

	int getTotalCount();

	void setTotalCount(int totalCount);
	
	boolean isFirstPage();
	
	boolean isLastPage();

	int getPrePage();
	
	int getNextPage();
}
