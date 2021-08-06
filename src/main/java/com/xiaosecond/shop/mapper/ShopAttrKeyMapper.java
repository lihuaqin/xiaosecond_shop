package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品属性名 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Repository
public interface ShopAttrKeyMapper extends BaseMapper<ShopAttrKeyVo> {

    List<Integer > getIdsByIdCategory(Long idCategory);


}
