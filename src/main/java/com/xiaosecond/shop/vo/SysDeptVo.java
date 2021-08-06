package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_dept")
public class SysDeptVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String fullname;

    private Integer num;

    private Long pid;

    private String pids;

    private String simplename;

    private String tips;

    private Integer version;


}
