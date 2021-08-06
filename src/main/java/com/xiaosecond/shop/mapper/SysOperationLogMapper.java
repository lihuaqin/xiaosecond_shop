package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Repository
public interface SysOperationLogMapper extends BaseMapper<SysOperationLogVo> {

    IPage<SysOperationLogVo> listPage(Page<SysOperationLogVo> page, @Param("param") Map<String,Object> param );

}
