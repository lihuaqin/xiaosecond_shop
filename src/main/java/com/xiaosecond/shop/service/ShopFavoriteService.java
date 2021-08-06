package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.ShopCartView;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.ShopCartVo;
import com.xiaosecond.shop.vo.ShopFavoriteVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户收藏 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
public interface ShopFavoriteService extends IService<ShopFavoriteVo> {

    PageResult<ShopFavoriteView> listPage(ShopFavoriteView shopFavoriteView , PageParam pageParam);

}
