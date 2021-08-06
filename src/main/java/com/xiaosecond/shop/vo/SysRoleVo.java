package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_role")
public class SysRoleVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long deptid;

    private String name;

    private Integer num;

    private Long pid;

    private String tips;

    private Integer version;


}
