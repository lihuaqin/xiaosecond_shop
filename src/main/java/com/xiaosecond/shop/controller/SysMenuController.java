package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.cache.Cache;
import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.security.AuthorizationUser;
import com.xiaosecond.shop.service.SysMenuService;
import com.xiaosecond.shop.service.SysUserService;
import com.xiaosecond.shop.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/menu")
@Slf4j
public class SysMenuController {


    @Autowired
    private Cache cache;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/listForRouter", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MENU})
    @ResponseBody
    public Response listForRouter() {
        log.info("start>>>>>>>>>>>");
        AuthorizationUser shiroUser = sysUserService.getAuthorizationInfo();
        log.info("listForRouter>>>>>>shiroUser:{}" , shiroUser.toString());
        return ResponseUtils.success(sysMenuService.getSideBarMenus(shiroUser.getRoleList()));
    }

}
