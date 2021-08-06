package com.xiaosecond.shop.service.impl;


import com.xiaosecond.shop.enums.OrderEnum;
import com.xiaosecond.shop.mapper.ShopCartMapper;
import com.xiaosecond.shop.mapper.ShopOrderMapper;
import com.xiaosecond.shop.mapper.ShopUserMapper;
import com.xiaosecond.shop.service.DashboardService;
import com.xiaosecond.shop.service.ShopUserService;
import com.xiaosecond.shop.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private ShopOrderMapper shopOrderMapper;
    @Autowired
    private ShopUserMapper shopUserMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;

    public Map getDashboardData(){
        long orderCount = shopOrderMapper.count();
        long userCount = shopUserMapper.count();
        long cartCount = shopCartMapper.count();
        Object orderSumPrice = shopOrderMapper.orderSumPrice(OrderEnum.OrderStatusEnum.UN_PAY.getId());
        Map result = Maps.newHashMap(
                "orderCount",orderCount,
                "userCount",userCount,
                "cartCount",cartCount,
                "orderSumPrice", orderSumPrice != null ?Double.valueOf(orderSumPrice.toString())/100:"0"
        );
        return result;
    }
}
