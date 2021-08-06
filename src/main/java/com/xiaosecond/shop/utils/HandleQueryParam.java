package com.xiaosecond.shop.utils;


import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.enums.OrderEnum;
import com.xiaosecond.shop.excpetion.GlobalExceptionHandler;
import com.xiaosecond.shop.excpetion.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class HandleQueryParam<T> {

    Class<T> clazz;
    public HandleQueryParam(Class<T> clazz) {
        this.clazz = clazz;
    }

    public  void viewVoToMap(T viewVo, Map<String ,Object> param)   {
        Field[] allFields = getAllFields(this.clazz);// 得到所有定义字段
        for (int i = 0; i < allFields.length; i++) {
            Field field = allFields[i];// 获得field
            field.setAccessible(true);// 设置实体类私有属性可访问
            QueryParam attr = field.getAnnotation(QueryParam.class);
            try {
                Object fieldValue = field.get(viewVo);
                if(attr != null && attr.isQuery() && fieldValue != null){
                    param.put(field.getName() , field.get(viewVo));
                }else if(attr != null && attr.isFuzzy() && !StringUtils.isEmpty(fieldValue) ){
                    param.put(field.getName() ,"%" + fieldValue + "%");
                }else if(attr != null && attr.isDateStart() && !StringUtils.isEmpty(fieldValue) ){
                    param.put(field.getName() , fieldValue + " 00:00:00");
                }else if(attr != null && attr.isDateEnd() && !StringUtils.isEmpty(fieldValue) ){
                    param.put(field.getName() , fieldValue + " 23:59:59");
                }else if(attr != null && attr.isOrderStatus() && !StringUtils.isEmpty(fieldValue) ){
                    param.put(field.getName() , OrderEnum.getStatusByStr(fieldValue.toString()));
                }

            }catch (Exception e){
                log.error("HandleQueryParam--viewVoToMap:{}" , GlobalExceptionHandler.getExceptionMessage(e));
                throw  new MyException( "处理查询参数异常","viewVoToMap");
            }
        }
    }

    /**
     * 获取本类及其父类的属性的方法
     * @param clazz 当前类对象
     * @return 字段数组
     */
    private static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }



}
