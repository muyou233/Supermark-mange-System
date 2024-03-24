package com.cskj.market.common;

/**
 * @Author lujun
 * @Description  统一返回结果类
 * @Date 2023/11/12 20:06
 * @Version 1.0
 */
public class Result {
    //响应状态码
    private Integer code;
    //信息
    private String message;
    //数据
    private Object data;

    //构造方法私有化
    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result build(ResultCodeEnum resultCodeEnum, Object data) {
        Result result = new Result(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
        if (null != data) {
            result.setData(data);
        }
        return result;
    }
    //响应成功的方法
    public static Result ok(Object data) {
        Result result = build(ResultCodeEnum.SUCCESS,  data);
        return result;
    }
}
