package com.it.entity;

import java.util.List;

/**
 * 页面对象的封装
 */
public class Page<T> {
    //每页显示的条数
    private Integer initSize=5;
    //总共有多少条数据
    private Integer countNum;
    //当前页数
    private Integer currentPage;
    //总共页数
    private Integer countPage;
    //上一页
    private Integer prePage;
    //下一页
    private Integer nextPage;
    //页面数据
    private List<T> pageData;

    public Integer getInitSize() {
        return initSize;
    }

    public void setInitSize(Integer initSize) {
        this.initSize = initSize;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "Page{" +
                "initSize=" + initSize +
                ", countNum=" + countNum +
                ", currentPage=" + currentPage +
                ", countPage=" + countPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", pageData=" + pageData +
                '}';
    }

    public Page(Integer initSize, Integer countNum, Integer currentPage, List<T> pageData) {
        this.initSize = initSize;
        this.countNum = countNum;
        this.currentPage = currentPage;
        this.pageData = pageData;
        int num=this.countNum/this.initSize;
        //如果总条数除以每页显示条数等于0，则为两个数的商，否则商加一
        this.countPage=this.countNum%this.initSize==0?num :num+1;
        //如果当前页小于等于1，前页等于第一页，否则等于当前页减一
        this.prePage=this.currentPage<=1?1:this.currentPage-1;
        //如果当前页大于等于总页码，下页等于总页码，否则等于当前页加一
        this.nextPage=this.currentPage>=countPage?countPage:this.currentPage+1;
    }

    public Page(Integer countNum, Integer currentPage, List<T> pageData) {
        this.countNum = countNum;
        this.currentPage = currentPage;
        this.pageData = pageData;
        int num=this.countNum/this.initSize;
        //如果总条数除以每页显示条数等于0，则为两个数的商，否则商加一
        this.countPage=this.countNum%this.initSize==0?num :num+1;
        //如果当前页小于等于1，前页等于第一页，否则等于当前页减一
        this.prePage=this.currentPage<=1?1:this.currentPage-1;
        //如果当前页大于等于总页码，下页等于总页码，否则等于当前页加一
        this.nextPage=this.currentPage>=countPage?countPage:this.currentPage+1;
    }
}
