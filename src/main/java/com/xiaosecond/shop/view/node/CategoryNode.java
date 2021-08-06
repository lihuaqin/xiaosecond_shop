package com.xiaosecond.shop.view.node;


import com.xiaosecond.shop.vo.ShopCategoryVo;
import lombok.Data;
import java.util.List;

@Data
public class CategoryNode extends ShopCategoryVo {
    private List<CategoryNode> children= null;
    public String getLabel(){
        return getName();
    }

}
