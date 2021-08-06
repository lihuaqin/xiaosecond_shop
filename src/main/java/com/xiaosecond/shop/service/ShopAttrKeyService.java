package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性名 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
public interface ShopAttrKeyService extends IService<ShopAttrKeyVo> {

    List<ShopAttrKeyVo> getByCategoryId(Long idCategory);


}
