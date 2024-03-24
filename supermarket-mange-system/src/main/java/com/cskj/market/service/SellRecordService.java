package com.cskj.market.service;

import com.cskj.market.mapper.GoodsCodeMapper;
import com.cskj.market.mapper.GoodsMapper;
import com.cskj.market.mapper.SellRecordMapper;
import com.cskj.market.pojo.Goods;
import com.cskj.market.pojo.SellRecord;
import com.cskj.market.pojo.vo.SellRecordDataVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/2 10:48
 */
@Service
@Transactional
public class SellRecordService {
    @Resource
    private SellRecordMapper sellRecordMapper;
    @Resource
    private GoodsMapper goodsMapper;
    //添加销售记录
    public void addSellRecord(SellRecord sellRecord){
        //添加售出记录
        sellRecordMapper.add(sellRecord);
        //创建一个Goods对象
        Goods goods = new Goods();
        goods.setId(sellRecord.getGoodsId());
        goods.setGoodsStock(sellRecord.getNum());
        //修改商品的库存量
        goodsMapper.updateStock(goods);
    }

    //获取销售记录列表
    public List<SellRecord> getList(){
        return sellRecordMapper.selectList();
    }

    public List<SellRecordDataVO> getPieData(){
        return sellRecordMapper.selectPieData();
    }
}
