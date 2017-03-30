package org.hill.jpa.entity;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hillawi on 30-03-17.
 */
public class PaginatedListWrapper<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer resultCount;
    private List<T> list;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<T> getList() {
        return Collections.unmodifiableList(list);
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
