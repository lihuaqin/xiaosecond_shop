package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.*;
import com.xiaosecond.shop.service.SysOperationLogService;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/log")
public class SysOperationLogController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    /**
     * 查询指定用户的操作日志列表
     */
    @RequestMapping("/queryByUser")
    @ResponseBody
    @RequiresPermissions(value = {Permission.LOG})
    public Response<PageResult<SysOperationLogVo>> listPage(SysOperationLogVo sysOperationLogVo , PageParam pageParam){
        return ResponseUtils.success(sysOperationLogService.listPage(sysOperationLogVo , pageParam));
    }

}
