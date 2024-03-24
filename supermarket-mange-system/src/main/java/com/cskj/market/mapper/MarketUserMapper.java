package com.cskj.market.mapper;

import com.cskj.market.pojo.MarketUser;
import org.apache.ibatis.annotations.Select;

public interface MarketUserMapper {
//    根据用户名来查询
    MarketUser selectByUsername(String username);

    @Select("select avatar from market_user where user_name=#{username}")
    String selectAvatar(String username);
}
