package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品SKU Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface ShopGoodsSkuMapper extends BaseMapper<ShopGoodsSkuVo> {
    List<ShopGoodsSkuVo> getByGoodId(Integer idGoods);
}
