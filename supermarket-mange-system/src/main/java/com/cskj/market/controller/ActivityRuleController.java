package com.cskj.market.controller;

import com.cskj.market.common.Result;
import com.cskj.market.pojo.ActivityRule;
import com.cskj.market.pojo.SellRecord;
import com.cskj.market.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/4 13:03
 */
@RestController
@RequestMapping("/activityrule")
@CrossOrigin
public class ActivityRuleController {
    @Resource
    private ActivityService activityService;
    //获取活动规则列表
    @GetMapping("/list")
    public Result getAllActivity() {
        List<ActivityRule> list = activityService.getAllActivity();
        return Result.ok(list);
    }

    //添加活动规则
    @PostMapping("/add")
    public Result addActivity(@RequestBody ActivityRule activityRule){
        activityService.addActivity(activityRule);
        return Result.ok(null);
    }

    //删除活动规则
    @GetMapping("/deleteById")
    public Result deleteActivityRule(Integer id){
        activityService.deleteActivityRule(id);
        return Result.ok(null);
    }
}
