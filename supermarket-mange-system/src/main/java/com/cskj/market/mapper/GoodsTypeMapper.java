package com.cskj.market.mapper;

import com.cskj.market.pojo.Goods;
import com.cskj.market.pojo.GoodsType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsTypeMapper {
    @Select("SELECT * FROM goods_type")
    List<GoodsType> selectAll();
}
