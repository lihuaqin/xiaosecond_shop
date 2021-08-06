package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.SysMenuVo;
import lombok.Data;

@Data
public class SysMenuView extends SysMenuVo {

    private Long parentId;

}
