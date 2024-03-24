package com.cskj.market.service;

import com.cskj.market.mapper.MarketUserMapper;
import com.cskj.market.pojo.MarketUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
//业务类，每个方法都是实现具体功能的业务
public class MarketUserService {
//    通过把容器中的MarketUserMapper接口代理类的实例带入进来
    @Resource
    private MarketUserMapper marketUserMapper;
//    登录方法
    public MarketUser login(String username){
        MarketUser marketUser = marketUserMapper.selectByUsername(username);
        return marketUser;
    }

    //获取用户头像
    public String getAvatar(String username){
        String s = marketUserMapper.selectAvatar(username);
        return s;

    }
}
