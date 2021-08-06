package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopUserService;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/shop/user")
public class ShopUserController {

    @Autowired
    private ShopUserService shopUserService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response<PageResult<ShopUserView>> listPage(ShopUserView shopUserView , PageParam pageParam){
        return ResponseUtils.success(shopUserService.listPage(shopUserView , pageParam));
    }

}
