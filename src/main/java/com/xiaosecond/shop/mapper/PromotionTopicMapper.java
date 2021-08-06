package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.view.PromotionTopicView;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.PromotionTopicVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 专题 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Repository
public interface PromotionTopicMapper extends BaseMapper<PromotionTopicVo> {

    IPage<PromotionTopicView> listPage(Page<PromotionTopicView> page, @Param("param") Map<String,Object> param );

}
