package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.mapper.ShopFavoriteMapper;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.view.PromotionTopicView;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.PromotionTopicVo;
import com.xiaosecond.shop.mapper.PromotionTopicMapper;
import com.xiaosecond.shop.service.PromotionTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 专题 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Service
public class PromotionTopicServiceImpl extends ServiceImpl<PromotionTopicMapper, PromotionTopicVo> implements PromotionTopicService {

    @Autowired
    private PromotionTopicMapper promotionTopicMapper;

    public PageResult<PromotionTopicView> listPage(PromotionTopicView promotionTopicView , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<PromotionTopicView> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<PromotionTopicView> handleQueryParam = new HandleQueryParam<PromotionTopicView>(PromotionTopicView.class);
        handleQueryParam.viewVoToMap(promotionTopicView , param);
        return new PageResult<>(promotionTopicMapper.listPage(page , param ));
    }
}
