package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosecond.shop.vo.ShopCategoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 商品类别 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Repository
public interface ShopCategoryMapper extends BaseMapper<ShopCategoryVo> {

    IPage<ShopCategoryVo> listPage(Page<ShopCategoryVo> page, @Param("param") Map<String,Object> param );

}
