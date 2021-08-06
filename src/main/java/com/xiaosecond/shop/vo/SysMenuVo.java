package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_menu")
public class SysMenuVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String code;

    /**
     * 組件配置
     */
    private String component;

    /**
     * 是否隐藏
     */
    private Integer hidden;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否是菜单1:菜单,0:按钮
     */
    private Integer ismenu;

    /**
     * 是否默认打开1:是,0:否
     */
    private Integer isopen;

    /**
     * 级别
     */
    private Integer levels;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer num;

    /**
     * 父菜单编号
     */
    private String pcode;

    /**
     * 递归父级菜单编号
     */
    private String pcodes;

    /**
     * 状态1:启用,0:禁用
     */
    private Integer status;

    /**
     * 鼠标悬停提示信息
     */
    private String tips;

    /**
     * 链接
     */
    private String url;


}
