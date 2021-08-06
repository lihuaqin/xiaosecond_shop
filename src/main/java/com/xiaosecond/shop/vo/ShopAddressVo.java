package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_address")
public class ShopAddressVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 用户id
     */
    private Long idUser;

    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 收件人
     */
    private String name;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 省
     */
    private String province;

    /**
     * 联系电话
     */
    private String tel;


}
