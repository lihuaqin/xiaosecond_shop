package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.SysNoticeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 通知 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface SysNoticeService extends IService<SysNoticeVo> {

    List<SysNoticeVo> list(SysNoticeVo sysNoticeVo);

}
