package com.cskj.market.mapper;

import com.cskj.market.pojo.ActivityRule;
import com.cskj.market.pojo.Goods;
import com.cskj.market.pojo.GoodsType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/2 15:26
 */
public interface ActivityRuleMapper {
    @Select("select * from activity_rule where goods_id=#{id}")
    ActivityRule selectByGoodsId(Integer id);
    @Select("SELECT * FROM activity_rule t1,goods t2 where t1.goods_id=t2.id")
    List<ActivityRule> selectAll();
    @Insert("insert into activity_rule(goods_id,base_money,reduce_money) values(#{goodsId},#{baseMoney},#{reduceMoney})")
    void add(ActivityRule activityRule);

    //使用Delete注解来删除活动规则
    @Delete("delete from activity_rule where goods_id=#{goodsId} and goods_id IS not NULL")
    void delete(Integer id);
}
