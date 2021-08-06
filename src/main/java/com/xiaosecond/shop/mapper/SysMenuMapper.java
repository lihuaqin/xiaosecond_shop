package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.view.SysMenuView;
import com.xiaosecond.shop.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuVo> {

    List<String> getResCodesByRoleId(Long roleId);

    List<String> getResUrlsByRoleId(Long roleId);

    List<SysMenuView> getMenusByRoleids(@Param("roleIds") List<Long> roleIds);

}
