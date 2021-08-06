package com.xiaosecond.shop.aspect;

import com.xiaosecond.shop.excpetion.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.List;



@Aspect
@Component
public class DeleteBatchIdsAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.xiaosecond.shop.service..*.deleteBatchIds(..))")
    public void deleteBatchIds(){}


    /**
     * 前置增强 在切入点的方法run之前要干的
     * @param joinPoint
     */
    @Before("deleteBatchIds()")
    public void beforDeleteBatchIds(JoinPoint joinPoint) {
        if(joinPoint == null || joinPoint.getArgs() == null || (List<Integer>)joinPoint.getArgs()[0] == null){
            throw new MyException("请传入必填参数" , "deleteBatchIds");
        }
        List<Integer>  ids = (List<Integer>)joinPoint.getArgs()[0];
        if(ids == null || ids.isEmpty()){
            throw new MyException("请勾选删除项" , "deleteBatchIds");
        }
    }

}
