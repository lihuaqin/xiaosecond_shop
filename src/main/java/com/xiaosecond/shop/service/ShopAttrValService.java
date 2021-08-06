package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.ShopAttrValVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性值 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
public interface ShopAttrValService extends IService<ShopAttrValVo> {

    void saveShopAttrValVo(ShopAttrValVo shopAttrValVo);

    Map<String , Object>  getAttrByCategoryAndGoods(Long idCategory);

}
