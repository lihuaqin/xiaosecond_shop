package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.utils.Lists;
import com.xiaosecond.shop.utils.LogObjectHolder;
import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.xiaosecond.shop.mapper.ShopGoodsSkuMapper;
import com.xiaosecond.shop.service.ShopGoodsSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 商品SKU 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuMapper, ShopGoodsSkuVo> implements ShopGoodsSkuService {

    @Autowired
    private ShopGoodsSkuMapper shopGoodsSkuMapper;

    public void saveShopGoodsSkuVo(ShopGoodsSkuVo shopGoodsSkuVo){
        List<String> codeArr = Arrays.asList(shopGoodsSkuVo.getCode().split(","));
        List<String> codeNameArr = Arrays.asList(shopGoodsSkuVo.getCodeName().split(","));
        Collections.sort(codeArr, new Comparator<String>() {
            @Override
            public int compare(String  s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        Collections.sort(codeNameArr, new Comparator<String>() {
            @Override
            public int compare(String  s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        String code = Lists.concat(codeArr,",");
        String codeName = Lists.concat(codeNameArr,",");
        shopGoodsSkuVo.setCode(code);
        shopGoodsSkuVo.setCodeName(codeName);
        QueryWrapper<ShopGoodsSkuVo> query = new QueryWrapper<>();
        query.eq("code",code).eq("id_goods" ,shopGoodsSkuVo.getIdGoods().intValue() );
        ShopGoodsSkuVo oldSku =  shopGoodsSkuMapper.selectOne(query);
        if(oldSku!=null){
            LogObjectHolder.me().set(oldSku);
            oldSku.setMarketingPrice(shopGoodsSkuVo.getMarketingPrice());
            oldSku.setPrice(shopGoodsSkuVo.getPrice());
            oldSku.setStock(shopGoodsSkuVo.getStock());
            shopGoodsSkuMapper.updateById(oldSku);
        }else{
            shopGoodsSkuVo.setIsDeleted(0);
            shopGoodsSkuMapper.insert(shopGoodsSkuVo);
        }
    }
}
