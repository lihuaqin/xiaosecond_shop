package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专题
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_promotion_topic")
public class PromotionTopicVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 专题文章
     */
    private Long idArticle;

    /**
     * 商品id列表
     */
    private String idGoodsList;

    /**
     * 阅读量
     */
    private Long pv;

    /**
     * 标题
     */
    private String title;


}
