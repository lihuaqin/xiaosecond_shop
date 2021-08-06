package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopCartService;
import com.xiaosecond.shop.service.ShopFavoriteService;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.ShopCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户收藏 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Controller
@RequestMapping("/shop/favorite")
public class ShopFavoriteController {
    @Autowired
    private ShopFavoriteService shopFavoriteService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response list(ShopFavoriteView shopFavoriteView, PageParam pageParam) {
        return ResponseUtils.success(shopFavoriteService.listPage(shopFavoriteView , pageParam));
    }
}
