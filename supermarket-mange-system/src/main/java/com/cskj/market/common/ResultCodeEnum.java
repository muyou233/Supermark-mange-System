package com.cskj.market.common;

/**
 * @Author lujun
 * @Description
 * @Date 2023/11/12 22:23
 * @Version 1.0
 */
public enum ResultCodeEnum {
    //通用的成功枚举成员
    SUCCESS(20000,"Success"),
    USERNAME_ERROR(501,"用户名错误"),
    PASSWORD_ERROR(503,"密码错误"),
    NOTLOGIN(504,"尚未成功登录"),
    USERNAME_USED(505,"该用户名已被使用,请更换!!"),

    ADD_GOODS_FAIL(506,"添加商品失败");
    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


