package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface SysOperationLogService extends IService<SysOperationLogVo> {

    PageResult<SysOperationLogVo> listPage(SysOperationLogVo sysOperationLogVo , PageParam pageParam);

}
