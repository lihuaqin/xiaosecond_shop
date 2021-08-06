package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品属性名
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_attr_key")
public class ShopAttrKeyVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 商品类别id
     */
    private Long idCategory;


}
