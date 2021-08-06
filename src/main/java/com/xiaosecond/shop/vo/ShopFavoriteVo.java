package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收藏
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_favorite")
public class ShopFavoriteVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long idGoods;

    /**
     * 用户id
     */
    private Long idUser;


}
