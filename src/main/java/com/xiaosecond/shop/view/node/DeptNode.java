package com.xiaosecond.shop.view.node;



import com.xiaosecond.shop.vo.SysDeptVo;
import java.util.ArrayList;
import java.util.List;

/**
 * DeptNode
 *
 */
public class DeptNode extends SysDeptVo {

    private List<DeptNode> children = new ArrayList<>(10);

    public List<DeptNode> getChildren() {
        return children;
    }

    public void setChildren(List<DeptNode> children) {
        this.children = children;
    }
}
