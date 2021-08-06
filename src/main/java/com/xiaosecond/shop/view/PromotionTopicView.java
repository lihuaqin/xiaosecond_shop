package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.CmsArticleVo;
import com.xiaosecond.shop.vo.PromotionTopicVo;
import lombok.Data;

@Data
public class PromotionTopicView extends PromotionTopicVo {

    private CmsArticleVo article;

    private String startDate;

    private String endDate;

}
