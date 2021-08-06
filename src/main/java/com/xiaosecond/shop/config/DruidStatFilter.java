package com.xiaosecond.shop.config;

import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
    initParams={
        @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
    }
)
@Slf4j
public class DruidStatFilter extends WebStatFilter{



    @Value("${spring.datasource.druid.statViewServlet.login-username}")
    private String loginUserNameDruid;
    @Value("${spring.datasource.druid.statViewServlet.login-password}")
    private String loginPasswordDruid;
    private final String DRUID_URL_FILTER_PATH  = "/js/common.js";
    private final String DRUID_LOGIN_PATH  = "druid/login.html";
    private final String DRUID_FILE_PATH  = "support/http/resources/js/common.js";
    private final String DRUID_HTML_FILE_PATH  = "support/http/resources/login.html";
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置response查询的码表
        response.setCharacterEncoding("UTF-8");
        //通过一个头"Content-type"告知客户端使用何种码表
        ((HttpServletResponse)response).setHeader("Content-type", "text/html;charset=UTF-8");
        if(getRequestURI((HttpServletRequest) request).contains(DRUID_LOGIN_PATH)){  //校验token
            try {
//                jwtTokenUtil.checkToken((HttpServletRequest)  request);
                //token 校验通过处理自动登陆druid
                response.resetBuffer();
                /** 改写druid的login.html文件内容，从而token校验通过后自动登录druid */
                response.getWriter().write(druidAutoLoginJs());
                return;
            }catch (Exception e){
                log.error("DruidStatFilter>>>>doFilter" , e );
            }
        }
        if(getRequestURI((HttpServletRequest) request).contains(DRUID_URL_FILTER_PATH)){  //去druid页面除底部标签
            response.resetBuffer();
            /** 获取common文件内容 */
            String text = Utils.readFromResource(DRUID_FILE_PATH);
            /** 利用正则表达式删除<footer class="footer">中的<a>标签 */
            text = text.replaceAll("<a.*?banner\"></a><br/>", "");
            text = text.replaceAll("powered.*?shrek.wang</a>", "");
            response.getWriter().write(text);
            return;
        }
        super.doFilter(request,response,chain);
    }

    /**
     * 自动登录druid  Js
     * @return
     * @throws IOException
     */
    public String druidAutoLoginJs() throws IOException {
        String text = Utils.readFromResource(DRUID_HTML_FILE_PATH);
        StringBuilder autoLoginCode = new StringBuilder();
        autoLoginCode.append("$('input[name=loginUsername]').val('"+ loginUserNameDruid +"');\n");
        autoLoginCode.append("$('input[name=loginPassword]').val('"+ loginPasswordDruid +"');\n");
        autoLoginCode.append("$.ajax({\n");
        autoLoginCode.append("  type: 'POST',\n");
        autoLoginCode.append("  url: 'submitLogin',\n");
        autoLoginCode.append("  data: $('#loginForm').serialize(),\n");
        autoLoginCode.append("  success: function(data) {\n");
        autoLoginCode.append("    if('success' == data)\n");
        autoLoginCode.append("       location.href = 'index.html';\n");
        autoLoginCode.append("    else {\n");
        autoLoginCode.append("      $('#alertInfo').show();\n");
        autoLoginCode.append("      $('#loginForm')[0].reset();\n");
        autoLoginCode.append("      $('input[name=loginUsername]').focus();\n");
        autoLoginCode.append("     }\n");
        autoLoginCode.append("  },\n");
        autoLoginCode.append("  dataType: 'text'\n");
        autoLoginCode.append("});\n");
        return text.replace("$(\"#loginBtn\").click(druid.login.login);" , autoLoginCode.toString());
    }

}
