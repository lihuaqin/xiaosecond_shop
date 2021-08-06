package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单明细
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_order_item")
public class ShopOrderItemVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 数量
     */
    private String count;

    /**
     * 商品id
     */
    private Long idGoods;

    /**
     * 所属订单id
     */
    private Long idOrder;

    /**
     * skuId
     */
    private Long idSku;

    /**
     * 单价
     */
    private String price;

    /**
     * 合计
     */
    private String totalPrice;


}
