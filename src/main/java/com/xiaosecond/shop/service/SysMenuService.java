package com.xiaosecond.shop.service;

import com.xiaosecond.shop.view.node.RouterMenu;
import com.xiaosecond.shop.vo.SysMenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface SysMenuService extends IService<SysMenuVo> {

    List<RouterMenu> getSideBarMenus(List<Long> roleIds);

}
