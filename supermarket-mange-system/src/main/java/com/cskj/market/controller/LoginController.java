package com.cskj.market.controller;

import com.cskj.market.common.Result;
import com.cskj.market.common.ResultCodeEnum;
import com.cskj.market.pojo.LoginForm;
import com.cskj.market.pojo.MarketUser;
import com.cskj.market.service.MarketUserService;
import com.cskj.market.utils.JwtHelper;
import com.cskj.market.utils.MD5Util;
import io.jsonwebtoken.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
//解决跨域问题
@CrossOrigin
public class LoginController {

    @Resource
    private MarketUserService marketUserService;

    //    @RequestMapping(value="/admin/user/login",method = RequestMethod.POST)简化
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
//        获取用户名
        String username = loginForm.getUsername();
//        获取密码
        String password = loginForm.getPassword();
        MarketUser marketUser = marketUserService.login(username);
        Result result = null;
        if (null != marketUser) {
//            用户名正确，再判断密码是否正确
            String pwd = MD5Util.encrypt(password);
            if (marketUser.getPassword().equals(pwd)) {
//                密码正确，登陆成功
                Map<String, Object> map = new HashMap<>();
//            生成Token，让用户请求带着token
                String token = JwtHelper.createToken(marketUser.getId(), username);
                map.put("token", token);
                result = Result.ok(map);
            } else {
//                密码错误
                result = Result.build(ResultCodeEnum.PASSWORD_ERROR, null);
            }
        } else {
//            用户名错误
            result = Result.build(ResultCodeEnum.USERNAME_ERROR, null);
        }


        return result;
    }

    //    获取用户信息,形参就是前端发送请求携带的token
    @GetMapping("/info")
    public Result getInfo(String token) {
        //从token中获取用户名
        String name = JwtHelper.getUserName(token);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        String avatar = marketUserService.getAvatar(name);
        map.put("avatar", avatar);
        return Result.ok(map);//{code:20000,message:"success",data:{name:"admin",avatar:“XXXXX”}}
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.ok(null);
    }
}
