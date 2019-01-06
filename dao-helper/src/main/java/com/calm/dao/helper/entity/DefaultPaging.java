package com.calm.dao.helper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author dingqihui
 */
public class DefaultPaging<T extends Serializable> implements Paging<T> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<? extends T> data;
    private int currentPage = 1;
    private int totalPage;
    private int pageSize = 20;
    private int totalCount;

    public DefaultPaging(int currentPage, int pageSize) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<? extends T> getData() {
        return data;
    }

    public void setData(List<? extends T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrent() {
        return currentPage;
    }

    public void setCurrent(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        setTotalPage((totalCount + pageSize - 1) / pageSize);
    }

    public int getTotal() {
        return totalCount;
    }

    public void setTotal(int totalCount) {
        setTotalCount(totalCount);
    }

    @Override
    public String toString() {
        return "DefaultPaging [data=" + data + ", currentPage=" + currentPage
                + ", totalPage=" + totalPage + ", pageSize=" + pageSize
                + ", totalCount=" + totalCount + "]";
    }

    @Override
    public boolean isFirstPage() {
        return currentPage == 1;
    }

    @Override
    public int getPrePage() {
        return currentPage - 1;
    }

    @Override
    public int getNextPage() {
        return currentPage + 1;
    }

    @Override
    public boolean isLastPage() {
        return currentPage >= totalPage;
    }
}
