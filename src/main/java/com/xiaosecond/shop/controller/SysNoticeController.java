package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.SysNoticeService;
import com.xiaosecond.shop.vo.SysNoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 通知 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Response list(SysNoticeVo sysNoticeVo) {
        return ResponseUtils.success(sysNoticeService.list(sysNoticeVo));
    }

}
