package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物流公司
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_express")
public class SysExpressVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
    private String code;

    /**
     * 是否禁用
     */
    private Integer disabled;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;


}
