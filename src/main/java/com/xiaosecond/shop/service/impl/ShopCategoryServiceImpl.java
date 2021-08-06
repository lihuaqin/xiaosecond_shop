package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.mapper.ShopAttrKeyMapper;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.mapper.ShopCategoryMapper;
import com.xiaosecond.shop.service.ShopCategoryService;
import com.xiaosecond.shop.utils.Lists;
import com.xiaosecond.shop.view.node.CategoryNode;
import com.xiaosecond.shop.vo.ShopCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Service
public class ShopCategoryServiceImpl  implements ShopCategoryService {

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Autowired
    private ShopAttrKeyMapper shopAttrKeyMapper;

    public PageResult<ShopCategoryVo> listPage(ShopCategoryVo shopCategoryVo , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopCategoryVo> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopCategoryVo> handleQueryParam = new HandleQueryParam<ShopCategoryVo>(ShopCategoryVo.class);
        handleQueryParam.viewVoToMap(shopCategoryVo , param);
        return new PageResult<>(shopCategoryMapper.listPage(page , param ));
    }

    public void save(ShopCategoryVo shopCategoryVo ){
        if(shopCategoryVo.getId()==null){
            shopCategoryMapper.insert(shopCategoryVo);
        }else {
            shopCategoryMapper.updateById(shopCategoryVo);
        }
    }

    public void deleteById(Integer id){
        shopCategoryMapper.deleteById(id);
    }

    public List<CategoryNode> getCategories() {
        QueryWrapper<ShopCategoryVo> query = new QueryWrapper<>();
        query.orderByAsc("sort");
        List<ShopCategoryVo> list = shopCategoryMapper.selectList(query);
        List<CategoryNode> nodes = Lists.newArrayList();
        for(ShopCategoryVo category:list){
            if(category.getPid()==null) {
                CategoryNode node = new CategoryNode();
                BeanUtils.copyProperties(category, node);
                nodes.add(node);
            }
        }
        for(CategoryNode node:nodes){
            for(ShopCategoryVo category:list){
                if(category.getPid()!=null&&category.getPid().intValue() == node.getId().intValue()){
                    CategoryNode child = new CategoryNode();
                    BeanUtils.copyProperties(category,child);
                    if(node.getChildren()==null){
                        node.setChildren(Lists.newArrayList());
                    }
                    node.getChildren().add(child);
                }
            }
        }
        return nodes;
    }

    public void changeShowIndex(Long idCategory, Boolean showIndex){
        ShopCategoryVo shopCategoryVo = shopCategoryMapper.selectById(idCategory);
        shopCategoryVo.setShowIndex(showIndex);
        shopCategoryMapper.updateById(shopCategoryVo);
    }
}
