package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import com.xiaosecond.shop.vo.ShopAttrValVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品属性值 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface ShopAttrValMapper extends BaseMapper<ShopAttrValVo> {

    List<ShopAttrValVo> getIdsByKeyList(@Param("keyList") List<ShopAttrKeyVo> keyList);

}
