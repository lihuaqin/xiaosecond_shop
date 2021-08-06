package com.xiaosecond.shop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryParam {
    /**
     * 字段开启转换模糊查，默认关闭
     * @return
     */
    boolean isFuzzy() default false;

    /**
     * 字段为查询字段，默认非查询字段
     * @return
     */
    boolean isQuery() default false;

    /**
     * 字段为起始时间段查询字段，默认非起始时间段查询
     * @return
     */
    boolean isDateStart() default false;
    /**
     * 字段为终结时间段查询字段，默认非终结时间段查询
     * @return
     */
    boolean isDateEnd() default false;

    /**
     * 字段为终结时间段查询字段，默认非终结时间段查询
     * @return
     */
    boolean isOrderStatus() default false;
}
