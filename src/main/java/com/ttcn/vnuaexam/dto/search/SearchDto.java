package com.ttcn.vnuaexam.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SearchDto implements Serializable {
    private int pageIndex = 1;
    private int pageSize = 10;
    private String sortField;
    private String sortDirection = "ASC";
    private String keyword;

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex != null && pageIndex > 0) {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        this.setPageIndex(pageIndex);
        this.setPageSize(pageSize);
    }

    public int getOffset() {
        return (this.pageIndex - 1) * this.pageSize;
    }

    public void setPageIndexAndPageSizeToExport() {
        this.pageIndex = 1;
        this.pageSize = 1000000;
    }

    public void setKeyword(final String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            this.keyword = keyword.trim();
        }
    }

    public void setSortField(final String sortField) {
        if (sortField != null && !sortField.isEmpty()) {
            this.sortField = sortField.trim();
        }
    }

    public void setSortDirection(final String sortDirection) {
        if (sortDirection != null && !sortDirection.isEmpty()) {
            this.sortDirection = sortDirection.trim();
        }
    }

    public SearchDto() {
    }
}