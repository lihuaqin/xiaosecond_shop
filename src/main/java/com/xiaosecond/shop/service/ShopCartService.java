package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.view.ShopOrderViewReq;
import com.xiaosecond.shop.vo.ShopCartVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface ShopCartService extends IService<ShopCartVo> {

    PageResult<ShopCartVo> listPage(ShopCartVo shopCartVo , PageParam pageParam);

}
