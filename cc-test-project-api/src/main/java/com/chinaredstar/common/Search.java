package com.chinaredstar.common;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by CC on 16/4/5.
 */
public class Search implements Serializable {

    String orderBy;

    int pageSize = 10;

    int page = 0;

    int nextPage = 0;

    int start = 0;

    Map<String, Object> condition;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        page = page == 0 ? 1 : page;
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public int getNextPage() {
        return getPage() - 1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getStart() {
        return getNextPage() * pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
