package com.cskj.market.mapper;

import com.cskj.market.pojo.SellRecord;
import com.cskj.market.pojo.vo.SellRecordDataVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/2 10:38
 */
public interface SellRecordMapper {
    @Insert("insert into sell_record(sell_time,goods_id,goods_name,num,price,receivable,receipts) values(now(),"+"#{goodsId},#{goodsName},#{num},#{price},#{receivable},#{receipts})")
    void add(SellRecord sellRecord);

    @Select("select * from sell_record")
    List<SellRecord> selectList();

    //查询饼图数据（销售金额）
    @Select("SELECT type_name name,SUM(receipts) value FROM sell_record t1,goods t2,goods_type t3 where t1.goods_id=t2.id and type_id=t3.id GROUP BY type_name")
    List<SellRecordDataVO> selectPieData();
}
