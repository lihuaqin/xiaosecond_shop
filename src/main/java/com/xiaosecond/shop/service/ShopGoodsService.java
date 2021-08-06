package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.ShopGoodsDetails;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
public interface ShopGoodsService extends IService<ShopGoodsVo> {

    PageResult<ShopGoodsView> listPage(ShopGoodsView shopGoodsView , PageParam pageParam);

    void saveShopGoodsVo(ShopGoodsVo shopGoodsVo);

    ShopGoodsDetails getDetail(Long id);
}
