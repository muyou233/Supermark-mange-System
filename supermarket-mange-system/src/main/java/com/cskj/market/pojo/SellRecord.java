package com.cskj.market.pojo;

import java.util.Date;

/**
 * @Author: muyou
 * @Date: 2023/12/2 10:34
 */
public class SellRecord {
    private Integer id;
    //售出时间
    private Date sellTime;
    private Integer goodsId;
    private String goodsName;
    //数量
    private Integer num;
    private Double price;
    private Double receivable;
    private Double receipts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReceivable() {
        return receivable;
    }

    public void setReceivable(Double receivable) {
        this.receivable = receivable;
    }

    public Double getReceipts() {
        return receipts;
    }

    public void setReceipts(Double receipts) {
        this.receipts = receipts;
    }
}
