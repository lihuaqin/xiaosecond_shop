package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类别banner关联表
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_category_banner_rel")
public class ShopCategoryBannerRelVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * banner id
     */
    private Long idBanner;

    /**
     * 类别id
     */
    private Long idCategory;


}
