package com.cskj.market.service;

import com.cskj.market.mapper.GoodsCodeMapper;
import com.cskj.market.pojo.GoodsCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/11/30 15:28
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCodeService {
    @Resource
    private GoodsCodeMapper goodsCodeMapper;
    //添加商品条码信息
    public void addGoodsCode(GoodsCode goodsCode){
        goodsCodeMapper.insert(goodsCode);
    }

    public List<GoodsCode> getAll(){
        return goodsCodeMapper.selectAll();
    }
    public void deleteByIdCode(Integer id){
        goodsCodeMapper.delete(id);
    }
}
