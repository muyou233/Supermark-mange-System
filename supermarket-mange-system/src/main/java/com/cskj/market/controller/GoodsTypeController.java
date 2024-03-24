package com.cskj.market.controller;

import com.cskj.market.common.Result;
import com.cskj.market.pojo.GoodsType;
import com.cskj.market.service.GoodsTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goodsType")
@CrossOrigin
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;
    //获取商品类型列表
    @GetMapping("/list")
    public Result getTypes(){
        List<GoodsType> allTypes = goodsTypeService.getAllTypes();
        Result result=Result.ok(allTypes);
        return result;
    }
}
