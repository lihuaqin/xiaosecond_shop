package com.xiaosecond.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaosecond.shop.vo.ShopCartVo;

import java.util.Map;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface DashboardService {

    Map getDashboardData();

}
