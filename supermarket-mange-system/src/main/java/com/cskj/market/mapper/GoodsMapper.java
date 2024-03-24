package com.cskj.market.mapper;

import com.cskj.market.pojo.Goods;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {
    Page<Goods> selectList(@Param("k") String keyWord);

    //使用Insert注解来书写插入语句
    @Insert("insert into goods(goods_name,price,publish_time,type_id,goods_desc,goods_stock) values (#{goodsName},#{price},now(),#{typeId},#{goodsDesc},#{goodsStock})")
    //把插入到表中的自增id字段值献给对应的属性
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void add(Goods goods);

    //使用Update注解来书写修改语句
    @Update("update goods set goods_name=#{goodsName},price=#{price},type_id=#{typeId},goods_desc=#{goodsDesc} where id=#{id}")
    void update(Goods goods);

    //删除商品
    @Delete("delete from goods where id=#{id}")
    void delete(Integer id);

    //修改商品库存量
    @Update("update goods set goods_stock=goods_stock-#{goodsStock} where id=#{id}")
    void updateStock(Goods goods);

    //根据id查询商品的名称，价格，编号
    @Select("select id,goods_name,price from goods where id=#{id}")
    Goods selectById(Integer id);
}
