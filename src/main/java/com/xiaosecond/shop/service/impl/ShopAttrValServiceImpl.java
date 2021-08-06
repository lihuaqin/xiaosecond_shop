package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.mapper.ShopAttrKeyMapper;
import com.xiaosecond.shop.mapper.ShopCategoryMapper;
import com.xiaosecond.shop.service.CommonService;
import com.xiaosecond.shop.service.ShopAttrKeyService;
import com.xiaosecond.shop.utils.Maps;
import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import com.xiaosecond.shop.vo.ShopAttrValVo;
import com.xiaosecond.shop.mapper.ShopAttrValMapper;
import com.xiaosecond.shop.service.ShopAttrValService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性值 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Service
public class ShopAttrValServiceImpl extends ServiceImpl<ShopAttrValMapper, ShopAttrValVo> implements ShopAttrValService {

    @Autowired
    private ShopAttrValMapper shopAttrValMapper;

    @Autowired
    private ShopAttrKeyService shopAttrKeyService;

    @Autowired
    private CommonService<ShopAttrValVo> commonService;

    public void saveShopAttrValVo(ShopAttrValVo shopAttrValVo){
        if(shopAttrValVo.getId() != null ){ //修改
            commonService.checkRepeat(shopAttrValVo.getAttrVal() , shopAttrValVo.getId(),"attr_val" , shopAttrValMapper);
            shopAttrValMapper.updateById(shopAttrValVo);
        }else{//新增
            commonService.checkRepeat(shopAttrValVo.getAttrVal() , "attr_val" , shopAttrValMapper);
            shopAttrValMapper.insert(shopAttrValVo);
        }
    }

    public Map<String , Object> getAttrByCategoryAndGoods(Long idCategory){
        List<ShopAttrKeyVo> keyList = shopAttrKeyService.getByCategoryId(idCategory);
        if(keyList == null || keyList.isEmpty()){
            return Maps.newHashMap(
                    "keyList", new ArrayList<>(),
                    "valList", new ArrayList<>()
            );
        }
        List<ShopAttrValVo> valList = shopAttrValMapper.getIdsByKeyList(keyList);
        return Maps.newHashMap(
                "keyList", keyList,
                "valList", valList
        );
    }
}
