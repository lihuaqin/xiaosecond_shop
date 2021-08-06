package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import com.xiaosecond.shop.mapper.SysOperationLogMapper;
import com.xiaosecond.shop.service.SysOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLogVo> implements SysOperationLogService {

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    public PageResult<SysOperationLogVo> listPage(SysOperationLogVo sysOperationLogVo , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<SysOperationLogVo> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<SysOperationLogVo> handleQueryParam = new HandleQueryParam<SysOperationLogVo>(SysOperationLogVo.class);
        handleQueryParam.viewVoToMap(sysOperationLogVo , param);
        return new PageResult<>(sysOperationLogMapper.listPage(page , param ));

    }
}
