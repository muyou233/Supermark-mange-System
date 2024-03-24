package com.cskj.market.pojo.vo;

/**
 * @Author: muyou
 * @Date: 2023/12/4 15:19
 */
//统计商品不同分类的销售数据
public class SellRecordDataVO {
    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
