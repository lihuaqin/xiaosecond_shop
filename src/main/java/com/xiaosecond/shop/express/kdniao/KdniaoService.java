package com.xiaosecond.shop.express.kdniao;


import com.xiaosecond.shop.cache.Cache;
import com.xiaosecond.shop.common.CfgKey;
import com.xiaosecond.shop.excpetion.MyException;
import com.xiaosecond.shop.express.ExpressApi;
import com.xiaosecond.shop.service.SysCfgService;
import com.xiaosecond.shop.utils.Base64Util;
import com.xiaosecond.shop.utils.JsonUtil;
import com.xiaosecond.shop.utils.MD5;
import com.xiaosecond.shop.utils.Maps;
import com.xiaosecond.shop.view.ShopExpressInfoView;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 快递鸟查询服务
 *
 * @author ：enilu
 * @date ：Created in 2020/5/31 10:27
 */
@Service
public class KdniaoService implements ExpressApi {
    private Logger logger = LoggerFactory.getLogger(KdniaoService.class);
    @Autowired
    private SysCfgService sysCfgService;


    @Override
    public ShopExpressInfoView realTimeQuery(String orderNo, String companyCode) {


        String url = sysCfgService.getCfgValue(CfgKey.API_KDNIAO_URL);
        String userId = sysCfgService.getCfgValue(CfgKey.API_KDNIAO_USERID);
        String apiKey = sysCfgService.getCfgValue(CfgKey.API_KDNIAO_APIKEY);
        logger.info("url:{}\nuserId:{}\napiKey:{}", url, userId, apiKey);
        Map appParams = Maps.newHashMap(
                "OrderCode", "",
                "ShipperCode", companyCode,
                "LogisticCode", orderNo
        );
        String jsonStr = JsonUtil.toJson(appParams);
        String datasign = null;
        try {
            datasign = URLEncoder.encode(Base64Util.base64Encode(MD5.getMD5String((jsonStr + apiKey)).toLowerCase().getBytes()));
            Map params = Maps.newHashMap(
                    "RequestType", "1002",
                    "EBusinessID", userId,
                    "RequestData", URLEncoder.encode(jsonStr, "UTF-8"),
                    "DataSign", datasign,
                    "DataType", "2"
            );
            Response response = Http.post2(url, params, 6000);
            logger.info(response.getContent());
            if (response.isOK()) {
                String content = response.getContent();
                KdniaoResponse kdniaoResponse = JsonUtil.fromJson(KdniaoResponse.class, content);
                ShopExpressInfoView expressInfo = new ShopExpressInfoView();
                List<Trace> list = kdniaoResponse.getTraces();
                Collections.reverse(list);
                expressInfo.setInfo(JsonUtil.toJson(list));
                int state = Integer.valueOf(kdniaoResponse.getState());
                switch (state){
                    case 2:
                        //-在途中,
                        expressInfo.setState(ShopExpressInfoView.STATE_ING);
                        break;
                    case 3:
                        //-签收,
                        expressInfo.setState(ShopExpressInfoView.STATE_FINISH);
                        break;
                    case 4:
                        //-问题件
                        expressInfo.setState(ShopExpressInfoView.STATE_ERROR);
                        break;
                }

                return expressInfo;
            }
        } catch (Exception e) {
            throw new MyException("查询失败","realTimeQuery");
        }
        return null;

    }
}
