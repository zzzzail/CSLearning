package com.qingtian.lcpes.base.page;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class PageDTO<T> implements Serializable {
    private int pageNum;
    private int pageSize;
    private int startRow;
    private long total;
    private int pages;
    private List<T> resultData;
    private T requestData;

    public void firstPage() {
        this.pageNum = 1;
        this.calculateStartAndEndRow();
    }

    public void lastPage() {
        this.pageNum = this.pages;
        this.calculateStartAndEndRow();
    }

    public void previousPage() {
        if (this.pageNum > 1) {
            --this.pageNum;
            this.calculateStartAndEndRow();
        }

    }

    public void nextPage() {
        if (this.pageNum < this.pages) {
            ++this.pageNum;
            this.calculateStartAndEndRow();
        }

    }

    public void gotoPage(int nPage) {
        if (nPage > 0 && nPage <= this.pages) {
            this.pageNum = nPage;
            this.calculateStartAndEndRow();
        }

    }

    public boolean isFirstPage() {
        return this.pageNum == 1;
    }

    public boolean isLastPage() {
        return this.pageNum == this.pages;
    }

    public PageDTO() {
    }

    public PageDTO(int pageNum, int pageSize) {
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            pageSize = 0;
        }

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.calculateStartAndEndRow();
    }

    public PageDTO(int pageNum, int pageSize, List<T> resultData) {
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            pageSize = 0;
        }

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.resultData = resultData;
        this.calculateStartAndEndRow();
    }

    public List<T> getResultData() {
        return this.resultData;
    }

    public PageDTO<T> setResultData(List<T> resultData) {
        this.resultData = resultData;
        return this;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum <= 0 ? 1 : pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return this.total;
    }

    public PageDTO<T> setTotal(long total) {
        this.total = total;
        if (total == -1L) {
            this.pages = 1;
            return this;
        }
        else {
            if (this.pageSize > 0) {
                this.pages = (int) (total / (long) this.pageSize + (long) (total % (long) this.pageSize == 0L ? 0 : 1));
            }
            else {
                this.pages = 0;
            }

            if (this.pageNum > this.pages) {
                this.pageNum = this.pages;
                this.calculateStartAndEndRow();
            }

            return this;
        }
    }

    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public T getRequestData() {
        return this.requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}
