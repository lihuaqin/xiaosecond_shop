package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.view.ShopUserView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosecond.shop.vo.ShopUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Repository
public interface ShopUserMapper extends BaseMapper<ShopUserVo> {

    Integer count();

    IPage<ShopUserView> listPage(Page<ShopUserView> page, @Param("param") Map<String,Object> param );

}
