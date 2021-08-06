package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_article")
public class CmsArticleVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    /**
     * 栏目id
     */
    private Long idChannel;

    /**
     * 文章题图ID
     */
    private String img;

    /**
     * 标题
     */
    private String title;


}
