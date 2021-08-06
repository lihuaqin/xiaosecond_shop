package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.SysExpressVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 物流公司 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
public interface SysExpressService extends IService<SysExpressVo> {

    List<SysExpressVo> queryAll();


}
