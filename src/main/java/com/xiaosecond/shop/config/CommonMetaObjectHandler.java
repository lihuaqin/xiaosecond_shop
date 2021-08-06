package com.xiaosecond.shop.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xiaosecond.shop.security.JwtUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充： 创建时间 、创建人、修改时间、修改人
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 创建时间
     */
    private static final String FIELD_SYS_CREATE_TIME = "createDate";

    /**
     * 创建人
     */
    private static final String FIELD_SYS_CREATER = "creater";

    /**
     * 修改时间
     */
    private static final String FIELD_SYS_UPDATE_TIME = "updateDate";

    /**
     * 修改人
     */
    private static final String FIELD_SYS_UPDATER = "updater";

    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentDate = new Date();
        // 插入创建时间
        if (metaObject.hasSetter(FIELD_SYS_CREATE_TIME)) {
            this.setFieldValByName(FIELD_SYS_CREATE_TIME , currentDate , metaObject);
        }
        // 插入创建人
        Integer creater = (Integer) metaObject.getValue(FIELD_SYS_CREATER);
        if(creater == null) creater = jwtUtil.getUserIdInt();
        if (metaObject.hasSetter(FIELD_SYS_CREATER) ) {
            this.setFieldValByName(FIELD_SYS_CREATER , creater , metaObject);
        }
        // 同时设置修改时间为当前插入时间
        if (metaObject.hasSetter(FIELD_SYS_UPDATE_TIME)) {
            this.setFieldValByName(FIELD_SYS_UPDATE_TIME , currentDate , metaObject);
        }
        // 同时设置修改时间为当前插入时间
        Integer updater = (Integer) metaObject.getValue(FIELD_SYS_CREATER);
        if(updater == null) updater = jwtUtil.getUserIdInt();
        if (metaObject.hasSetter(FIELD_SYS_UPDATER) ) {
            this.setFieldValByName(FIELD_SYS_UPDATER , updater , metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(FIELD_SYS_UPDATE_TIME)) {
            this.setFieldValByName(FIELD_SYS_UPDATE_TIME, new Date(), metaObject);
        }
        if (metaObject.hasSetter(FIELD_SYS_UPDATER) &&  jwtUtil.getUserIdInt() != null) {
            this.setFieldValByName(FIELD_SYS_UPDATER , jwtUtil.getUserIdInt() , metaObject);
        }
    }
}
