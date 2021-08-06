package com.xiaosecond.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.annotation.BussinessLog;
import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.CmsBannerService;
import com.xiaosecond.shop.utils.JsonUtil;
import com.xiaosecond.shop.utils.StringUtils;
import com.xiaosecond.shop.vo.CmsBannerVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Controller
@RequestMapping("/banner")
public class CmsBannerController {

    @Autowired
    private CmsBannerService cmsBannerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.BANNER})
    @ResponseBody
    public Response list(@RequestParam(required = false) String title) {
        QueryWrapper<CmsBannerVo> query = new QueryWrapper<>();
        if(StringUtils.isNotBlank(title)) query.like("title" , title );
        return ResponseUtils.success(cmsBannerService.list(query));
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑banner", key = "title")
    @RequiresPermissions(value = {Permission.BANNER_EDIT})
    @ResponseBody
    public Response save( CmsBannerVo banner) {
        if(StringUtils.isNotEmpty(banner.getParam()) && !JsonUtil.isJson(banner.getParam())){
            return ResponseUtils.failure("参数必须为json格式");
        }
        cmsBannerService.saveOrUpdate(banner);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除banner", key = "id")
    @RequiresPermissions(value = {Permission.BANNER_DEL})
    @ResponseBody
    public Response remove(Integer id) {
        cmsBannerService.removeById(id);
        return ResponseUtils.success();
    }
}
