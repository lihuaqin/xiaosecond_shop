package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.annotation.BussinessLog;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopOrderService;
import com.xiaosecond.shop.utils.DateUtil;
import com.xiaosecond.shop.utils.DateUtils;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.view.ShopOrderViewReq;
import com.xiaosecond.shop.vo.ShopOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/shop/order")
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response<PageResult<ShopOrderView>> list(ShopOrderViewReq shopOrderView , PageParam pageParam) {
        if(StringUtils.isNotEmpty(shopOrderView.getDate())){
            Date[] rangeDate = DateUtil.getDateRange(shopOrderView.getDate());
            shopOrderView.setStartDate(DateUtils.dateTime(rangeDate[0]));
            shopOrderView.setEndDate(DateUtils.dateTime(rangeDate[1]));
        }
        return ResponseUtils.success(shopOrderService.listPage(shopOrderView ,pageParam));
    }

    /**
     * 获取订单统计信息
     * todo 真实生产可以考虑将订单数量信息通过队列形式更新在redis等缓存中，然后从缓存获取，这里暂时从数据库直接统计
     * @return
     */
    @RequestMapping(value = "/getOrderStatistic", method = RequestMethod.GET)
    @ResponseBody
    public Response getOrderStatistic(){
        return ResponseUtils.success(shopOrderService.getOrderStatistic());
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
    @BussinessLog(value = "管理员添加备注", key = "id")
    @ResponseBody
    public Response comment(@PathVariable("id") Long id,@RequestParam("message") String message) {
        shopOrderService.addComment(id, message);
        return ResponseUtils.success();
    }

    @RequestMapping(value = "/sendOut/{id}", method = RequestMethod.POST)
    @BussinessLog(value = "发货", key = "id")
    @ResponseBody
    public Response sendOut( ShopOrderVo shopOrderVo) {
        shopOrderService.send(shopOrderVo);
        return ResponseUtils.success();
    }

    @RequestMapping(value="/getExpressInfo/{orderSn}")
    @ResponseBody
    public Response getExpressInfo(@PathVariable("orderSn")String orderSn){
        return ResponseUtils.success(shopOrderService.getExpressInfo(orderSn));
    }

}
