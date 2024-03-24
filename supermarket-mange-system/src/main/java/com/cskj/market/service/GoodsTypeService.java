package com.cskj.market.service;

import com.cskj.market.mapper.GoodsTypeMapper;
import com.cskj.market.pojo.GoodsType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsTypeService {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    public List<GoodsType> getAllTypes(){
       return goodsTypeMapper.selectAll();
    }

}
