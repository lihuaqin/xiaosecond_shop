package com.xiaosecond.shop.common;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页参数类
 */
@Data
@ApiModel("分页参数")
public class PageParam {

    @ApiModelProperty("页码")
    @TableField(exist = false)
    private Integer page;

    @ApiModelProperty("记录条数")
    @TableField(exist = false)
    private Integer limit;
}
