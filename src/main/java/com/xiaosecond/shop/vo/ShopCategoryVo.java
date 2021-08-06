package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品类别
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_category")
public class ShopCategoryVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String descript;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 名称
     */
    private String name;

    /**
     * 父类别
     */
    private Long pid;

    /**
     * 是否显示在首页
     */
    private Boolean showIndex;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 链接地址
     */
    private String url;


}
