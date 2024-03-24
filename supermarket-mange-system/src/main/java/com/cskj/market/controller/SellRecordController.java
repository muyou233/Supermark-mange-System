package com.cskj.market.controller;

import com.cskj.market.common.Result;
import com.cskj.market.mapper.ActivityRuleMapper;
import com.cskj.market.pojo.ActivityRule;
import com.cskj.market.pojo.GoodsType;
import com.cskj.market.pojo.SellRecord;
import com.cskj.market.pojo.vo.SellRecordDataVO;
import com.cskj.market.service.ActivityService;
import com.cskj.market.service.SellRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/2 19:06
 */
@RestController
@RequestMapping("/sellManage")
@CrossOrigin
public class SellRecordController {
    @Resource
    private ActivityRuleMapper activityRuleMapper;
    @Resource
    private SellRecordService sellRecordService;

    @GetMapping("/activityRule")
    public Result getActivityRule(Integer id){
        ActivityRule activityRule = activityRuleMapper.selectByGoodsId(id);
        if (activityRule != null) {
           return Result.ok(activityRule);
        }
        return Result.ok(null);
    }

    //添加销售记录
    @PostMapping("/add")
    public Result addSellRecord(@RequestBody SellRecord sellRecord){
        sellRecordService.addSellRecord(sellRecord);
        return Result.ok(null);
    }

    //获取销售记录列表
    @GetMapping("/recordList")
    public Result getList(){
        List<SellRecord> list = sellRecordService.getList();
        return Result.ok(list);
    }

    @GetMapping("/pieData")
    public Result getPieData(){
        List<SellRecordDataVO> pieData = sellRecordService.getPieData();
        return Result.ok(pieData);
    }
}
