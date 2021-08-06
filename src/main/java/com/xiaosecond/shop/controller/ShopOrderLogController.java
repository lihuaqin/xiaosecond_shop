package com.xiaosecond.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopOrderLogService;
import com.xiaosecond.shop.vo.ShopOrderLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 订单日志 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/shop/order/log")
public class ShopOrderLogController {
    @Autowired
    private ShopOrderLogService shopOrderLogService;
    @RequestMapping(value = "/queryByIdOrder/{idOrder}",method = RequestMethod.GET)
    @ResponseBody
    public Response queryByIdOrder(@PathVariable("idOrder") Long idOrder){
        return ResponseUtils.success(shopOrderLogService.queryByIdOrder(idOrder));
    }
}
