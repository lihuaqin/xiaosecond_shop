package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.view.node.CategoryNode;
import com.xiaosecond.shop.vo.ShopCategoryVo;

import java.util.List;

/**
 * <p>
 * 商品类别 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
public interface ShopCategoryService {

    PageResult<ShopCategoryVo> listPage(ShopCategoryVo shopCategoryVo , PageParam pageParam);

    void save(ShopCategoryVo shopCategoryVo );

    void deleteById(Integer id);

    List<CategoryNode> getCategories();

    void changeShowIndex(Long idCategory, Boolean showIndex);

}
