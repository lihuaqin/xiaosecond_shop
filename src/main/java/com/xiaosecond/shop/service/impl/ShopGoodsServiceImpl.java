package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.mapper.ShopGoodsSkuMapper;
import com.xiaosecond.shop.mapper.ShopUserMapper;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.utils.LogObjectHolder;
import com.xiaosecond.shop.view.ShopGoodsDetails;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import com.xiaosecond.shop.mapper.ShopGoodsMapper;
import com.xiaosecond.shop.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsMapper, ShopGoodsVo> implements ShopGoodsService {

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    @Autowired
    private ShopGoodsSkuMapper shopGoodsSkuMapper;

    public PageResult<ShopGoodsView> listPage(ShopGoodsView shopGoodsView , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopGoodsView> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopGoodsView> handleQueryParam = new HandleQueryParam<ShopGoodsView>(ShopGoodsView.class);
        handleQueryParam.viewVoToMap(shopGoodsView , param);
        return new PageResult<>(shopGoodsMapper.listPage(page , param ));
    }

    public void saveShopGoodsVo(ShopGoodsVo shopGoodsVo){
        List<ShopGoodsSkuVo> skuList = shopGoodsSkuMapper.getByGoodId(shopGoodsVo.getId());
        if(StringUtils.isEmpty(shopGoodsVo.getPrice())){ //多规格
            int stock = 0;
            for(ShopGoodsSkuVo sku:skuList){
                stock+=sku.getStock();
            }
            shopGoodsVo.setStock(stock);
            shopGoodsVo.setPrice(skuList.get(0).getPrice());
        }else{//单规格
            //如果配置了price，说明是单规格商品，则将之前配置的sku库存皆设置为0;这里最好不要删除sku记录，避免之前下过的订单无法正确关联
            for(ShopGoodsSkuVo sku:skuList){
                sku.setStock(0);
                shopGoodsSkuMapper.updateById(sku);
            }
        }
        ShopGoodsVo old = shopGoodsMapper.selectById(shopGoodsVo.getId());
        LogObjectHolder.me().set(old);
        shopGoodsMapper.updateById(shopGoodsVo);
    }

    public ShopGoodsDetails getDetail(Long id){
        ShopGoodsVo goods = shopGoodsMapper.selectById(id.intValue());
        List<ShopGoodsSkuVo> skuList = shopGoodsSkuMapper.getByGoodId(goods.getId());
        return new ShopGoodsDetails(goods , skuList);
    }

}
