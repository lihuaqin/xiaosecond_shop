package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import com.xiaosecond.shop.mapper.ShopAttrKeyMapper;
import com.xiaosecond.shop.service.ShopAttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性名 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Service
public class ShopAttrKeyServiceImpl extends ServiceImpl<ShopAttrKeyMapper, ShopAttrKeyVo> implements ShopAttrKeyService {

    @Autowired
    private ShopAttrKeyMapper shopAttrKeyMapper;

    public List<ShopAttrKeyVo> getByCategoryId(Long idCategory){
        QueryWrapper<ShopAttrKeyVo> query = new QueryWrapper<>();
        query.eq("id_category" , idCategory);
        return shopAttrKeyMapper.selectList(query);
    }


}
