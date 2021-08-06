package com.xiaosecond.shop.excpetion;


import com.xiaosecond.shop.common.RetCode;
import lombok.Data;

@Data
public class MyException extends RuntimeException{


    private Integer code;  //异常状态码

    private String message;  //异常信息

    private String method;   //发生的方法，位置等

    private String descinfo;   //描述

    public MyException(String message, String method ) {
        this.code= RetCode.FAILURE.getCode();
        this.message=message;
        this.method=method;
        this.descinfo= null;
    }

    public MyException(Integer code, String message, String method ) {
        this.code=code;
        this.message=message;
        this.method=method;
        this.descinfo= null;
    }

    public MyException(Integer code, String message, String method, String descinfo) {
        this.code=code;
        this.message=message;
        this.method=method;
        this.descinfo=descinfo;
    }


}

