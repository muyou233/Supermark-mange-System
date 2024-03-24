package com.cskj.market.service;

import com.cskj.market.mapper.ActivityRuleMapper;
import com.cskj.market.pojo.ActivityRule;
import com.cskj.market.pojo.GoodsType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: muyou
 * @Date: 2023/12/2 15:27
 */
@Service
public class ActivityService {
    @Resource
    private ActivityRuleMapper activityRuleMapper;
    //根据商品编号获取活动规则
    public ActivityRule getActivityRuleById(Integer id){
        return activityRuleMapper.selectByGoodsId(id);
    }
    public List<ActivityRule> getAllActivity(){
        return activityRuleMapper.selectAll();
    }

    //添加活动规则
    public void addActivity(ActivityRule activityRule) {
        activityRuleMapper.add(activityRule);
    }

    //删除活动规则
    public void deleteActivityRule(Integer id){
        activityRuleMapper.delete(id);
    }
}
