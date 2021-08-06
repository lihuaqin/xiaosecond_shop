package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.utils.Lists;
import com.xiaosecond.shop.view.SysMenuView;
import com.xiaosecond.shop.view.node.MenuMeta;
import com.xiaosecond.shop.view.node.RouterMenu;
import com.xiaosecond.shop.vo.SysMenuVo;
import com.xiaosecond.shop.mapper.SysMenuMapper;
import com.xiaosecond.shop.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuVo> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    /**
     * 获取左侧菜单树
     * @return
     */
    public List<RouterMenu> getSideBarMenus(List<Long> roleIds) {

        List<RouterMenu> list = transferRouteMenu(sysMenuMapper.getMenusByRoleids(roleIds));
        List<RouterMenu> result = generateRouterTree(list);
        for (RouterMenu menuNode : result) {
            if (!menuNode.getChildren().isEmpty()) {
                sortRouterTree(menuNode.getChildren());
                for (RouterMenu menuNode1 : menuNode.getChildren()) {
                    if (!menuNode1.getChildren().isEmpty()) {
                        sortRouterTree(menuNode1.getChildren());
                    }
                }
            }
        }
        sortRouterTree(result);
        return result;
    }



    private List<RouterMenu> transferRouteMenu( List<SysMenuView> menus) {
        List<RouterMenu> routerMenus = new ArrayList<>();
        for (SysMenuView sysMenuVo:menus) {
            if(sysMenuVo.getStatus() == 0) continue;

            if(StringUtils.isEmpty(sysMenuVo.getComponent())){
                continue;
            }
            RouterMenu menu = new RouterMenu();
            menu.setPath(sysMenuVo.getUrl());
            menu.setName(sysMenuVo.getName());
            MenuMeta meta = new MenuMeta();
            meta.setIcon(sysMenuVo.getIcon());
            //如果使用前端vue-i18n对菜单进行国际化，则title設置为code，且code需要与国际化资源文件中的key值一致
            meta.setTitle(sysMenuVo.getCode());
            menu.setNum(sysMenuVo.getNum());
            menu.setParentId(sysMenuVo.getParentId());
            menu.setComponent(sysMenuVo.getComponent());
            menu.setId((long)sysMenuVo.getId());
            menu.setMeta(meta);
            menu.setHidden(sysMenuVo.getHidden() == 1);
            routerMenus.add(menu);
        }
        return routerMenus;
    }

    private List<RouterMenu> generateRouterTree(List<RouterMenu> list) {
        List<RouterMenu> result = new ArrayList<>(20);
        Map<Long, RouterMenu> map = Lists.toMap(list, "id");
        for (Map.Entry<Long, RouterMenu> entry : map.entrySet()) {
            RouterMenu menuNode = entry.getValue();
            if (menuNode.getParentId().intValue() != 0) {
                RouterMenu parentNode = map.get(menuNode.getParentId());
                if(parentNode!=null) {
                    parentNode.getChildren().add(menuNode);
                }
            } else {
                result.add(menuNode);
            }
        }
        return result;

    }


    private void sortRouterTree(List<RouterMenu> list) {
        Collections.sort(list, new Comparator<RouterMenu>() {
            @Override
            public int compare(RouterMenu o1, RouterMenu o2) {
                return o1.getNum() - o2.getNum();
            }
        });
    }

}
