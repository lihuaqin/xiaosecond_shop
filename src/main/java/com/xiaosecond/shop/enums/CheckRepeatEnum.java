package com.xiaosecond.shop.enums;

import org.apache.commons.lang3.StringUtils;



public enum CheckRepeatEnum {

    name( "名称"), account("账号") ,attr_val("属性值");

    private String info;

    CheckRepeatEnum(String info) {
        this.info = info;
    }

    public static String getInfoByFieldName(String fieldName) {
        for (CheckRepeatEnum checkRepeatEnum:CheckRepeatEnum.values()) {
            if(StringUtils.equals(fieldName,checkRepeatEnum.name())){
                return checkRepeatEnum.info;
            }
        }
        return null;
    }
}
