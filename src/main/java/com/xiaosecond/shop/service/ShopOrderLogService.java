package com.xiaosecond.shop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.vo.ShopOrderLogVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单日志 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
public interface ShopOrderLogService extends IService<ShopOrderLogVo> {

    List<ShopOrderLogVo> queryByIdOrder(Long idOrder);

}
