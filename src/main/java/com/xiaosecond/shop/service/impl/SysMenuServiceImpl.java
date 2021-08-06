package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.excpetion.MyException;
import com.xiaosecond.shop.service.CommonService;
import com.xiaosecond.shop.utils.Lists;
import com.xiaosecond.shop.view.SysMenuView;
import com.xiaosecond.shop.view.node.MenuMeta;
import com.xiaosecond.shop.view.node.MenuNode;
import com.xiaosecond.shop.view.node.RouterMenu;
import com.xiaosecond.shop.view.node.TreeSelectNode;
import com.xiaosecond.shop.vo.ShopAttrValVo;
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

    @Autowired
    private CommonService<SysMenuVo> commonService;

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


    public List<MenuNode> getMenus(){
        List<MenuNode> list = transferMenuNode(sysMenuMapper.getMenus());
        List<MenuNode> result = generateTree(list);
        for (MenuNode menuNode : result) {
            if (!menuNode.getChildren().isEmpty()) {
                sortTree(menuNode.getChildren());
                for (MenuNode menuNode1 : menuNode.getChildren()) {
                    if (!menuNode1.getChildren().isEmpty()) {
                        sortTree(menuNode1.getChildren());
                    }
                }
            }
        }
        sortTree(result);
        return result;
    }

    private List<MenuNode> generateTree(List<MenuNode> list) {
        List<MenuNode> result = new ArrayList<>(20);
        Map<Long, MenuNode> map = Lists.toMap(list, "id");
        for (Map.Entry<Long, MenuNode> entry : map.entrySet()) {
            MenuNode menuNode = entry.getValue();

            if (menuNode.getParentId().intValue() != 0) {
                MenuNode parentNode = map.get(menuNode.getParentId());
                parentNode.getChildren().add(menuNode);
            } else {
                result.add(menuNode);
            }
        }
        return result;
    }

    private void sortTree(List<MenuNode> list) {
        Collections.sort(list, new Comparator<MenuNode>() {
            @Override
            public int compare(MenuNode o1, MenuNode o2) {
                return o1.getNum() - o2.getNum();
            }
        });
    }
    private List<MenuNode> transferMenuNode(List<SysMenuView> menus) {
        List<MenuNode> menuNodes = new ArrayList<>();
        for (SysMenuView menu:menus) {
            MenuNode menuNode = new MenuNode();
            menuNode.setId(menu.getId().longValue());
            menuNode.setIcon(menu.getIcon());
            menuNode.setParentId(menu.getParentId());
            menuNode.setName(menu.getName());
            menuNode.setUrl(menu.getUrl());
            menuNode.setLevels(menu.getLevels());
            menuNode.setIsmenu(menu.getIsmenu());
            menuNode.setNum(menu.getNum());
            menuNode.setCode(menu.getCode());
            if(menu.getComponent() != null)menuNode.setComponent(menu.getComponent());
            menuNode.setHidden(menu.getHidden() == 1);
            menuNode.setPcode(menu.getPcode());
            menuNodes.add(menuNode);
        }
        return menuNodes;
    }

    public List<TreeSelectNode> tree(){
        List<MenuNode> list = transferMenuNode(sysMenuMapper.getMenus());
        List<TreeSelectNode> treeSelectNodes = Lists.newArrayList();
        for (MenuNode menuNode : list) {
            TreeSelectNode tsn = transfer(menuNode);
            treeSelectNodes.add(tsn);
        }
        return treeSelectNodes;
    }

    public TreeSelectNode transfer(MenuNode node) {
        TreeSelectNode tsn = new TreeSelectNode();
        tsn.setId(node.getCode());
        tsn.setLabel(node.getName());
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            List<TreeSelectNode> children = Lists.newArrayList();
            for (MenuNode child : node.getChildren()) {
                children.add(transfer(child));
            }
            tsn.setChildren(children);
        }
        return tsn;
    }

    public void saveSysMenuVo(SysMenuVo menu){
        //判断是否存在该编号
        commonService.checkRepeat(menu.getCode() , menu.getId(),"code" , sysMenuMapper);
        menuSetPcode(menu);
        if(menu.getId() == null){
            sysMenuMapper.insert(menu);
        }else{
            sysMenuMapper.updateById(menu);
        }

    }
    public void menuSetPcode(SysMenuVo menu) {
        if (StringUtils.isEmpty(menu.getPcode()) || "0".equals(menu.getPcode())) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            QueryWrapper<SysMenuVo> query = new QueryWrapper<>();
            query.eq("code" , menu.getPcode());
            SysMenuVo pMenu = sysMenuMapper.selectOne(query);
            Integer pLevels = pMenu.getLevels();
            menu.setPcode(pMenu.getCode());
            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new MyException("编号和父编号一致" , "menuSetPcode");
            }
            menu.setLevels(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }

    public void delMenuContainSubMenus(Long id){
        SysMenuVo menu = sysMenuMapper.selectById(id);
        //删除所有子菜单
        sysMenuMapper.delByPcode("%[" + menu.getCode() + "]%");
        //删除当前菜单
        sysMenuMapper.deleteById(id);
    }
}
