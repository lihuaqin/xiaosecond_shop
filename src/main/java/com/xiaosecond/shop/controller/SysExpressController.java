package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.SysExpressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 物流公司 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/sys/express")
public class SysExpressController {

    @Autowired
    private SysExpressService sysExpressService;
    /**
     * 获取全部非禁用的物流公司列表
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.EXPRESS})
    @ResponseBody
    public Response queryAll() {
        return ResponseUtils.success(sysExpressService.queryAll());
    }

}
