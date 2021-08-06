package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.SysExpressVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物流公司 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface SysExpressMapper extends BaseMapper<SysExpressVo> {

    List<SysExpressVo> queryAll();

}
