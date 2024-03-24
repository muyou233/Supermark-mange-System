package com.cskj.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//处理前端请求
@RestController

public class HelloController {
    @RequestMapping(value="/hello")
    public String sayHello(){
        return "Hello,Springboot!";
    }
}
