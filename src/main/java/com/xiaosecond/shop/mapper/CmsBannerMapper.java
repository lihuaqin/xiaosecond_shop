package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.CmsBannerVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Repository
public interface CmsBannerMapper extends BaseMapper<CmsBannerVo> {

    List<CmsBannerVo> getBannerByCategoryId(Integer categoryId);
}
