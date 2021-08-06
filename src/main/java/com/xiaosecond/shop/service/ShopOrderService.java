package com.xiaosecond.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.view.ShopOrderViewReq;
import com.xiaosecond.shop.vo.ShopExpressInfoVo;
import com.xiaosecond.shop.vo.ShopOrderVo;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface ShopOrderService extends IService<ShopOrderVo> {

    PageResult<ShopOrderView> listPage(ShopOrderViewReq shopOrderView , PageParam pageParam);

    Map<String , Object> getOrderStatistic();

    void addComment(Long id, String message);

    void send(ShopOrderVo shopOrderVo);

    ShopExpressInfoVo getExpressInfo(String orderSn);

}
