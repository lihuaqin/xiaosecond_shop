package com.xiaosecond.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {

    @Autowired
    private HttpServletRequest request;


    public  String getToken(){
        return request.getHeader("Authorization");
    }




}
