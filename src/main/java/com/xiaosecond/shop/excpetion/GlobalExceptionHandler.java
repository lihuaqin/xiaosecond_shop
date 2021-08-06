package com.xiaosecond.shop.excpetion;


import com.xiaosecond.shop.common.CommonResult;
import com.xiaosecond.shop.common.RetCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(Exception e){
        log.info("捕获异常：{}",getExceptionMessage(e));
        return new CommonResult(RetCode.EXCEPTION.getCode(), RetCode.EXCEPTION.getMsg(),null);
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public CommonResult myExceptionHandler(MyException e){
        log.info("捕获异常：{},method：{}",e.getMessage() , e.getMethod());
        return new CommonResult(e.getCode(),e.getMessage(),null);
    }


    public static String getExceptionMessage(Exception e)
    {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }

}
