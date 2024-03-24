package com.cskj.market;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cskj.market.mapper")
@EnableTransactionManagement
public class MarketManageApp {
    public static void main(String[] args) {
        SpringApplication.run(MarketManageApp.class,args);
    }
}
