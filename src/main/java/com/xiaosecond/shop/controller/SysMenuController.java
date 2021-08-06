package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.annotation.BussinessLog;
import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.security.AuthorizationUser;
import com.xiaosecond.shop.service.SysMenuService;
import com.xiaosecond.shop.service.SysUserService;
import com.xiaosecond.shop.utils.LogObjectHolder;
import com.xiaosecond.shop.vo.SysMenuVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list() {
        return ResponseUtils.success(sysMenuService.getMenus());
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public Response tree() {
        return ResponseUtils.success(sysMenuService.tree());
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑菜单", key = "name")
    @RequiresPermissions(value = {Permission.MENU_EDIT})
    @ResponseBody
    public Response save( SysMenuVo menu) {
        sysMenuService.saveSysMenuVo(menu);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除菜单", key = "id")
    @RequiresPermissions(value = {Permission.MENU_DEL})
    @ResponseBody
    public Response remove(@RequestParam Long id) {
        sysMenuService.delMenuContainSubMenus(id);
        return ResponseUtils.success();
    }


}
