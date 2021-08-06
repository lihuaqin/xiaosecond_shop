package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.ShopExpressInfoVo;


public class ShopExpressInfoView extends ShopExpressInfoVo {
    /**
     * 在途中
     */
    public static  final Integer STATE_ING=0;
    /**
     * 已签收
     */
    public static  final Integer STATE_FINISH=1;
    /**
     * 问题件
     */
    public static  final Integer STATE_ERROR=-1;



}
