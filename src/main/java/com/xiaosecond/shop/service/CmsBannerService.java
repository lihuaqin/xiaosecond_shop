package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.CmsBannerVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
public interface CmsBannerService extends IService<CmsBannerVo> {

    List<CmsBannerVo> getBannerByCategoryId(Integer categoryId);


}
