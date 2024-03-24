package com.cskj.market.pojo.vo;

/**
 * @Author: muyou
 * @Date: 2023/11/24 15:10
 */
public class GoodsVO {
    private int pageNum;
    private int pageSize;
    private String keyWord;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
