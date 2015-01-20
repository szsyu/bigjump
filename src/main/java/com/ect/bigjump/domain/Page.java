package com.ect.bigjump.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类,用于组装分页对象,非持久化类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-01
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -3497133030858285545L;

    /**
     * 返回某一页的记录列表
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private int allRow;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 不包含页面数字的Url
     */
    private String basePageUrl;

    /**
     * 是否为第一页
     */
    private boolean isFirstPage;

    /**
     * 是否为最后一页
     */
    private boolean isLastPage;

    /**
     * 是否有前一页
     */
    private boolean hasPreviousPage;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage;

    public String getBasePageUrl() {
        return basePageUrl;
    }

    public void setBasePageUrl(String basePageUrl) {
        this.basePageUrl = basePageUrl;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return currentPage == 1;
    }

    public void setFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isLastPage() {
        return currentPage == totalPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return currentPage != 1;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return currentPage != totalPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public void init() {
        this.isLastPage = currentPage == totalPage ? true : false;
        this.isFirstPage = currentPage == 1 ? true : false;
        this.hasPreviousPage = currentPage > 1 ? true : false;
        this.hasNextPage = currentPage < totalPage ? true : false;
    }

    /**
     * 静态方法,计算总页数,供外部直接通过类名调用
     *
     * @param pageSize 每页记录数
     * @param allRow   总记录数
     * @return 总页数
     */
    public static int countTotalPage(int pageSize, int allRow) {
        int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
                / pageSize + 1;
        return totalPage;
    }

    /**
     * 静态方法,计算当前页开始记录
     *
     * @param pageSize    每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(int pageSize, int currentPage) {
        int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * 计算当前页,若为0或者请求中没有"?page=",则用1代替
     *
     * @param page
     * @return
     */
    public static int countCurrentPage(int page) {
        int curPage = page == 0 ? 1 : page;
        return curPage;
    }
}
