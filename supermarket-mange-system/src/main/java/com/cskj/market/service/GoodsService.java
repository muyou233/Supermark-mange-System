package com.cskj.market.service;

import com.cskj.market.mapper.GoodsMapper;
import com.cskj.market.pojo.Goods;
import com.cskj.market.pojo.MarketUser;
import com.cskj.market.pojo.vo.GoodsVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class) //作用是控制业务方法要在同一个事务中执行，所有的方法都执行成功才能提交
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    public Page<Goods> getGoodsList(GoodsVO goodsVO){
        //开启分页插件
        PageHelper.startPage(goodsVO.getPageNum(),goodsVO.getPageSize());
        Page<Goods> page = goodsMapper.selectList(goodsVO.getKeyWord());
        return page;
    }

    //添加商品
    public void addGoods(Goods goods){
        goodsMapper.add(goods);
    }

    //修改商品
    public void  updateGoods(Goods goods){
        goodsMapper.update(goods);
    }

    //删除商品
    public void deleteGoods(Integer id){
        goodsMapper.delete(id);
    }

    public Goods getGoodsById(Integer id){
        return goodsMapper.selectById(id);
    }
}
