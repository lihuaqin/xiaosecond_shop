package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品属性值
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_attr_val")
public class ShopAttrValVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值
     */
    private String attrVal;

    /**
     * 属性id
     */
    private Long idAttrKey;


}
