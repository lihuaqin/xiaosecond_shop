package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface ShopGoodsMapper extends BaseMapper<ShopGoodsVo> {
    IPage<ShopGoodsView> listPage(Page<ShopGoodsView> page, @Param("param") Map<String,Object> param );
}
