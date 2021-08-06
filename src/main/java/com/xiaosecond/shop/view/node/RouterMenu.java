package com.xiaosecond.shop.view.node;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosecond.shop.utils.Lists;
import lombok.Data;

import java.util.List;


@Data
public class RouterMenu {
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long parentId;
    private String path;
    private String component;
    private String name;
    private Integer num;
    private Boolean hidden=false;
    private MenuMeta meta;
    private List<RouterMenu> children = Lists.newArrayList();

}
