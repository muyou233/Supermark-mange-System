package com.cskj.market.controller;

import com.cskj.market.common.Result;
import com.cskj.market.common.ResultCodeEnum;
import com.cskj.market.pojo.Goods;
import com.cskj.market.pojo.GoodsCode;
import com.cskj.market.pojo.vo.GoodsVO;
import com.cskj.market.service.ActivityService;
import com.cskj.market.service.GoodsCodeService;
import com.cskj.market.service.GoodsService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsCodeService goodsCodeService;
    @Resource
    private ActivityService activityService;

    @PostMapping("/list")
    public Result getList(@RequestBody GoodsVO goodsVO){
        //获取商品列表数据
        Page<Goods> page = goodsService.getGoodsList(goodsVO);
        //创建Map,保存总记录数以及当前页的数据集合
        Map<String,Object> map=new HashMap<>();
        map.put("total",page.getTotal());
        map.put("records",page);
        Result result = Result.ok(map);
        return result;
    }

    @PostMapping("/add")
    public Result addGoods(@RequestBody Goods goods){
        //CTRL+ALT+T选择try/catch来捕获异常
        goodsService.addGoods(goods);
        GoodsCode goodsCode = new GoodsCode();
        goodsCode.setGid(goods.getId());
        String code = UUID.randomUUID().toString().replaceAll("-","");
        goodsCode.setGcode(code);
        goodsCodeService.addGoodsCode(goodsCode);
        //正常执行,返回一个正常的Result结果
        return Result.ok(null);

    }

    //编辑修改商品
    @PostMapping("/update")
    public Result updateGoods(@RequestBody Goods goods){
        goodsService.updateGoods(goods);
        return Result.ok(null);
    }

    //调用service中的删除商品的业务方法删除选中商品以及删除商品条码
    @GetMapping("/deleteById")
    public Result deleteGoods(Integer id){
        goodsService.deleteGoods(id);
        goodsCodeService.deleteByIdCode(id);
        activityService.deleteActivityRule(id);
        return Result.ok(null);
    }

    @GetMapping("/goodsCodeList")
    public Result getGoodsCodeList(){
        List<GoodsCode> all = goodsCodeService.getAll();
        return Result.ok(all);
    }

    //根据id来获取商品信息
    @GetMapping("/getGoods")
    public Result getGoodsById(Integer id){
        Goods goods = goodsService.getGoodsById(id);
        return Result.ok(goods);
    }
}
