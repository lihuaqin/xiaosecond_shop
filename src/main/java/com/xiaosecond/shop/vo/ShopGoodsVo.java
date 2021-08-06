package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_goods")
public class ShopGoodsVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 产品简介
     */
    private String descript;

    /**
     * 产品详情
     */
    private String detail;

    /**
     * 大图相册列表,以逗号分隔
     */
    private String gallery;

    /**
     * 类别id
     */
    private Long idCategory;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否人气商品
     */
    private Boolean isHot;

    /**
     * 是否新品推荐
     */
    private Boolean isNew;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    /**
     * 收藏数
     */
    private Integer likeNum;

    /**
     * 名称
     */
    @QueryParam(isFuzzy = true)
    private String name;

    /**
     * 小图
     */
    private String pic;

    /**
     * 价格
     */
    private String price;

    /**
     * 库存数量
     */
    private Integer stock;


}
