package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.PromotionTopicView;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.PromotionTopicVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 专题 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
public interface PromotionTopicService extends IService<PromotionTopicVo> {

    PageResult<PromotionTopicView> listPage(PromotionTopicView promotionTopicView , PageParam pageParam);

}
