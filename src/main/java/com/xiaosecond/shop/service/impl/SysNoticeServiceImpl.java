package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.vo.SysNoticeVo;
import com.xiaosecond.shop.mapper.SysNoticeMapper;
import com.xiaosecond.shop.service.SysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeVo> implements SysNoticeService {

    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Override
    public List<SysNoticeVo> list(SysNoticeVo sysNoticeVo) {
        Map<String,Object> param = new HashMap<>();
        HandleQueryParam<SysNoticeVo> handleQueryParam = new HandleQueryParam<SysNoticeVo>(SysNoticeVo.class);
        handleQueryParam.viewVoToMap(sysNoticeVo , param);
        return sysNoticeMapper.list(param);
    }
}
