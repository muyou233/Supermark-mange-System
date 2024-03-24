package com.cskj.market.pojo;

/**
 * @Author lujun
 * @Description
 * @Date 2023/12/1 17:35
 * @Version 1.0
 */
public class ActivityRule {
    private Integer goodsId;
    private Double baseMoney;
    private Double reduceMoney;
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(Double baseMoney) {
        this.baseMoney = baseMoney;
    }

    public Double getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(Double reduceMoney) {
        this.reduceMoney = reduceMoney;
    }
}
