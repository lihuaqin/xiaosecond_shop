package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_cart")
public class ShopCartVo extends BaseVo {

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
     * skuId
     */
    private Long idSku;

    /**
     * 用户id
     */
    private Long idUser;




}
