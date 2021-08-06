package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.utils.JsonUtil;
import com.xiaosecond.shop.utils.Lists;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 快递信息
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_express_info")
public class ShopExpressInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;



    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 所属订单id
     */
    private Long idOrder;

    /**
     * 详细信息
     */
    private String info;

    /**
     * 快递单号
     */
    private String shippingSn;

    /**
     * 状态:0在途中,1:已签收,-1问题件
     */
    private Integer state;

    public List<Map> infoList;



    public  List<Map>  getInfoList(){
        if(StringUtils.isNotEmpty(info)){
            return JsonUtil.fromJsonAsList(Map.class,getInfo());
        }
        return Lists.newArrayList();
    }

    public String getStateStr(){
        String result = "";
        switch (state){
            case 0:
                result = "在途中";
                break;
            case 1:
                result = "已签收";
                break;
            case -1:
                result = "问题件";
                break;

        }
        return result;
    }


}
