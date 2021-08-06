package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.log.LogManager;
import com.xiaosecond.shop.log.LogTaskFactory;
import com.xiaosecond.shop.mapper.SysLoginLogMapper;
import com.xiaosecond.shop.mapper.SysOperationLogMapper;
import com.xiaosecond.shop.security.JwtUtil;
import com.xiaosecond.shop.service.SysUserService;
import com.xiaosecond.shop.utils.HttpUtil;
import com.xiaosecond.shop.utils.MD5;
import com.xiaosecond.shop.security.JwtUser;
import com.xiaosecond.shop.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 账号 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginLogMapper  sysLoginLogMapper;

    /**
     * 用户登录<br>
     * 1，验证没有注册<br>
     * 2，验证密码错误<br>
     * 3，登录成功
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String userName,
                          @RequestParam("password") String password){
        //1,
        SysUserVo user = sysUserService.findByAccount(userName);
        if (user == null) {
            return ResponseUtils.failure("该用户不存在");
        }
        String passwdMd5 = MD5.md5(password, user.getSalt());
        //2,
        if (!user.getPassword().equals(passwdMd5)) {
            return ResponseUtils.failure("输入的密码错误");
        }
        String token = sysUserService.loginForToken(new JwtUser(user));
        Map<String, String> result = new HashMap<>(1);
        log.info("token:{}",token);
        result.put("token", token);
        LogManager.me().executeLog(LogTaskFactory.loginLog((long)user.getId(), HttpUtil.getIp() , sysLoginLogMapper));
        return ResponseUtils.success(result);
    }


    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Response info(){
        return sysUserService.getUserInfo();
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public Response logout(){
        sysUserService.logout();
        return ResponseUtils.success();
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @param rePassword
     * @return
     */
    @RequestMapping(value = "/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    public Response updatePwd( String oldPassword,String password, String rePassword){
        sysUserService.updatePwd(oldPassword,password,rePassword);
        return ResponseUtils.success();
    }


}
