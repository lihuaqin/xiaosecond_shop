package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.CfgKey;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.enums.OrderEnum;
import com.xiaosecond.shop.express.kdniao.KdniaoService;
import com.xiaosecond.shop.mapper.ShopExpressInfoMapper;
import com.xiaosecond.shop.mapper.ShopOrderLogMapper;
import com.xiaosecond.shop.mapper.ShopOrderMapper;
import com.xiaosecond.shop.security.JwtUtil;
import com.xiaosecond.shop.service.ShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosecond.shop.service.SysCfgService;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.utils.Maps;
import com.xiaosecond.shop.view.ShopExpressInfoView;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.view.ShopOrderViewReq;
import com.xiaosecond.shop.vo.ShopExpressInfoVo;
import com.xiaosecond.shop.vo.ShopOrderLogVo;
import com.xiaosecond.shop.vo.ShopOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrderVo> implements ShopOrderService {

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    private ShopOrderLogMapper shopOrderLogMapper;

    @Autowired
    private ShopExpressInfoMapper shopExpressInfoMapper;

    @Autowired
    private SysCfgService sysCfgService;

    @Autowired
    private KdniaoService kdniaoService;

    @Autowired
    private JwtUtil jwtUtil;

    public PageResult<ShopOrderView> listPage(ShopOrderViewReq shopOrderView , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopOrderView> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopOrderViewReq> handleQueryParam = new HandleQueryParam<ShopOrderViewReq>(ShopOrderViewReq.class);
        handleQueryParam.viewVoToMap(shopOrderView , param);
        return new PageResult<>(shopOrderMapper.listPage(page , param ));
    }

    public Map<String , Object> getOrderStatistic(){
        List<Object> list = shopOrderMapper.countStatus();
        Map result = Maps.newHashMap();
        for(Object obj:list){
            Map<Integer ,Integer> map = (Map<Integer ,Integer>)obj;
            String statusStr = OrderEnum.getStatusStr((Integer) map.get("status"));
            result.put(statusStr,map.get("count"));
        }
        return  result;
    }

    public void addComment(Long id, String message){
        ShopOrderVo shopOrderVo =new ShopOrderVo();
        shopOrderVo.setId(id.intValue());
        shopOrderVo.setAdminMessage(message);
        shopOrderMapper.updateById(shopOrderVo);
        String descript = "管理员(" + jwtUtil.getUsername() + ")添加备注：" + message;
        saveOrderLog(id , descript);
    }

    public void send(ShopOrderVo shopOrderVo){
        shopOrderVo.setStatus(OrderEnum.SENDED);
        shopOrderMapper.updateById(shopOrderVo);
        String descript = "管理员(" + jwtUtil.getUsername() + ")已发货";
        saveOrderLog(shopOrderVo.getId().longValue() , descript);
    }

    private void saveOrderLog(Long id, String descript) {
        ShopOrderLogVo orderLog = new ShopOrderLogVo();
        orderLog.setIdOrder(id);
        orderLog.setDescript(descript);
        shopOrderLogMapper.insert(orderLog);
    }

    public ShopExpressInfoVo getExpressInfo(String orderSn){
        ShopOrderView order = shopOrderMapper.getByOrderSn(orderSn);
        ShopExpressInfoView expressInfo = shopExpressInfoMapper.getByOrderId(order.getId());

        if ((expressInfo == null
                || expressInfo.getState() != ShopExpressInfoView.STATE_FINISH) &&
                order.getStatus() == OrderEnum.OrderStatusEnum.SENDED.getId()) {
            //远程查询

            ShopExpressInfoView apiResponse = kdniaoService.realTimeQuery(order.getShippingSn(), order.getExpress().getCode());
            if (StringUtils.isNotEmpty(apiResponse.getInfo())) {
                expressInfo = shopExpressInfoMapper.getByOrderId(order.getId());
                if (expressInfo == null) {
                    expressInfo = new ShopExpressInfoView();
                    expressInfo.setIdOrder(order.getId().longValue());
                    expressInfo.setExpressCompany(order.getExpress().getName());
                    expressInfo.setShippingSn(order.getShippingSn());
                    expressInfo.setState(apiResponse.getState());
                }
                expressInfo.setInfo(apiResponse.getInfo());
                expressInfo.setState(apiResponse.getState());
                shopExpressInfoMapper.updateById(expressInfo);
            }
        }
        return expressInfo;
    }

}
