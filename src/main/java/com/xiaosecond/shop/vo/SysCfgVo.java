package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_cfg")
public class SysCfgVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String cfgDesc;

    /**
     * 参数名
     */
    private String cfgName;

    /**
     * 参数值
     */
    private String cfgValue;


}
