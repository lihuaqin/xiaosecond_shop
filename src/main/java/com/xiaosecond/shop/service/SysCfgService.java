package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.SysCfgVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统参数 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
public interface SysCfgService extends IService<SysCfgVo> {

    String getCfgValue(String cfgName);

}
