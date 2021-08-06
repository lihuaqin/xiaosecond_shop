package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopCartService;
import com.xiaosecond.shop.vo.ShopCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/shop/cart")
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response list(ShopCartVo shopCartVo, PageParam pageParam) {
        return ResponseUtils.success(shopCartService.listPage(shopCartVo , pageParam));
    }

}
