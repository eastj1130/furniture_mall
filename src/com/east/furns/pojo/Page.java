package com.east.furns.pojo;

import java.util.List;

public class Page<T> {
    public static Integer PAGE_SIZE_DEFAULT = 3;
    private Integer pageNo;
    private Integer pageSize;
    private List<T> items;
    private Integer totalPage;

    private Integer totalItems;
    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize, List<T> items, Integer totalPage, Integer totalItems, String url) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.items = items;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
        this.url = url;
    }

    public static Integer getPageSizeDefault() {
        return PAGE_SIZE_DEFAULT;
    }

    public static void setPageSizeDefault(Integer pageSizeDefault) {
        PAGE_SIZE_DEFAULT = pageSizeDefault;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", totalPage=" + totalPage +
                ", totalItems=" + totalItems +
                ", url='" + url + '\'' +
                '}';
    }
}
