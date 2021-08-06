package com.xiaosecond.shop.config;

import com.alibaba.druid.util.Utils;
import com.xiaosecond.shop.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * token 拦截器
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    /** token校验 白名单 **/
    private  String[] NOT_FILTER = {"/webjars/","doc.html","/login" ,"/swagger-resources" , "/LogWebSocket" , "/error" ,"/initDb/"};

    private final String DRUID_FILE_PATH  = "/js/common.js";




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.contains(DRUID_FILE_PATH)){
            return removeDruidFilterRegistrationBean(response );
        }
        for (int i = 0; i <NOT_FILTER.length ; i++) {
            if(url.contains(NOT_FILTER[i]))
                return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean removeDruidFilterRegistrationBean( HttpServletResponse response ) throws IOException {
        String text = Utils.readFromResource(DRUID_FILE_PATH);
        // 正则替换banner, 除去底部的广告信息
        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
        text = text.replaceAll("powered.*?shrek.wang</a>", "");
        response.getWriter().write(text);
        return true;
    }

}
