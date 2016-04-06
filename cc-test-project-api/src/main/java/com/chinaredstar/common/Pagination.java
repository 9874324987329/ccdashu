package com.chinaredstar.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements Serializable {

    int pageNo = 1;

    int pageSize = 10;

    int count = 0;

    private int nearlySize = 5;

    List<T> records = new ArrayList<T>();

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public boolean getHasPrePage() {
        return this.getCurrentPage() != 1;
    }

    public int getCurrentPage() {
        return pageNo;
    }

    public int[] getNearlyPageNum() {
        int[] nearlyPage = null;
        try {

            if (getTotalPages() <= 1) {
                return new int[0];
            }
            int leftBorder = this.getCurrentPage() - this.nearlySize > 1 ? this.getCurrentPage() - this.nearlySize : 1;
            int rightBorder = (this.getCurrentPage() + this.nearlySize > this.getTotalPages()) ? this.getTotalPages() : this.getCurrentPage() + this.nearlySize;
            nearlyPage = new int[rightBorder - leftBorder + 1];
            int startPos = 0;
            for (int i = leftBorder; i <= rightBorder; i++) {
                nearlyPage[startPos] = i;
                startPos++;
            }
        } catch (Exception e) {
        }
        return nearlyPage;
    }

    public int getTotalPages() {
        if (count == 0 || pageSize == 0)
            return 1;
        else {
            return (count + pageSize - 1) / pageSize;
        }
    }

    public boolean getHasNextPage() {
        return getCurrentPage() < getTotalPages();
    }
}
