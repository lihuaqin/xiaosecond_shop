package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.ShopUserView;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface ShopUserService  {

    PageResult<ShopUserView> listPage(ShopUserView shopUserView , PageParam pageParam);

}
