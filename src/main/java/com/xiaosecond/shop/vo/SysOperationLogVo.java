package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_operation_log")
public class SysOperationLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String classname;

    private String logname;

    private String logtype;

    /**
     * 详细信息
     */
    private String message;

    private String method;

    private String succeed;

    @QueryParam(isQuery = true)
    private Integer userid;


}
