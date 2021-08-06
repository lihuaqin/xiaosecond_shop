package com.xiaosecond.shop.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 *
 */
@Data
@ApiModel("分页结果封装对象")
public class PageResult<T> implements Serializable {
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("当前页结果")
    private List<T> records;


    public PageResult(long total, List<T> rows) {
        super();
        this.total = total;
        this.records = rows;
    }

    public PageResult(IPage<T> page){
        this.records = page.getRecords();
        this.total = page.getTotal();
    }

    public PageResult(Page<T> list) {
        if (list.getSize() > 0) {
            this.records = list.getRecords();
            this.total = list.getTotal();
        }
    }



}
