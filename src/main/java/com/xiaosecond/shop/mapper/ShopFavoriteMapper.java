package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.ShopCartVo;
import com.xiaosecond.shop.vo.ShopFavoriteVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 用户收藏 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Repository
public interface ShopFavoriteMapper extends BaseMapper<ShopFavoriteVo> {

    IPage<ShopFavoriteView> listPage(Page<ShopFavoriteView> page, @Param("param") Map<String,Object> param );

}
