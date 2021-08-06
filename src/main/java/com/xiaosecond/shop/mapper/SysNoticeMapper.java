package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.SysNoticeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Repository
public interface SysNoticeMapper extends BaseMapper<SysNoticeVo> {

    List<SysNoticeVo> list(@Param("param") Map<String,Object> param);

}
