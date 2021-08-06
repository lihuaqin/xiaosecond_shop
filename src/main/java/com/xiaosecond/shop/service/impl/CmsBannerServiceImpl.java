package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.vo.CmsBannerVo;
import com.xiaosecond.shop.mapper.CmsBannerMapper;
import com.xiaosecond.shop.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBannerVo> implements CmsBannerService {

    @Autowired
    private CmsBannerMapper cmsBannerMapper;

    public List<CmsBannerVo> getBannerByCategoryId(Integer categoryId){
        return cmsBannerMapper.getBannerByCategoryId(categoryId);
    }



}
