package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品SKU
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_goods_sku")
public class ShopGoodsSkuVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * sku编码,格式:逗号分割的属性值id
     */
    private String code;

    /**
     * sku名称,格式:逗号分割的属性值
     */
    private String codeName;

    /**
     * 商品id
     */
    private Long idGoods;

    /**
     * 是否删除1:是,0:否
     */
    private Integer isDeleted;

    /**
     * 市场价,原价
     */
    private String marketingPrice;

    /**
     * 价格
     */
    private String price;

    /**
     * 库存
     */
    private Integer stock;


}
