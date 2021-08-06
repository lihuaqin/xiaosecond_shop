package com.xiaosecond.shop.express;


import com.xiaosecond.shop.view.ShopExpressInfoView;

/**
 * @author ：enilu
 * @date ：Created in 2021/1/2 23:13
 */
public interface ExpressApi {
    /**
     * 查询快递实时信息
     * @param orderNo 快递单号
     * @param companyCode 快递公司编码
     * @return
     */
    ShopExpressInfoView realTimeQuery(String orderNo, String companyCode) ;
}
