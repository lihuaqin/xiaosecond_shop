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
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_banner")
public class CmsBannerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * banner图id
     */
    private String idFile;

    /**
     * 界面
     */
    private String page;

    /**
     * 参数
     */
    private String param;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 点击banner跳转到url
     */
    private String url;


}
