package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品SKU 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSkuVo> {

    void saveShopGoodsSkuVo(ShopGoodsSkuVo shopGoodsSkuVo);

}
