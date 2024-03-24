package com.cskj.market.mapper;

import com.cskj.market.pojo.GoodsCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/11/30 15:26
 */
public interface GoodsCodeMapper {
    @Select("select * from goods_code")
    List<GoodsCode> selectAll();
    @Insert("insert into goods_code values (#{gid},#{gcode})")
    void insert(GoodsCode goodsCode);
    @Delete("delete from goods_code where gid=#{gid}")
    void delete(Integer id);
}
