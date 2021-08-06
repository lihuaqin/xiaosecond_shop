package com.xiaosecond.shop.log;


import com.xiaosecond.shop.enums.LogSucceed;
import com.xiaosecond.shop.enums.LogType;
import com.xiaosecond.shop.excpetion.GlobalExceptionHandler;
import com.xiaosecond.shop.mapper.SysLoginLogMapper;
import com.xiaosecond.shop.mapper.SysOperationLogMapper;
import com.xiaosecond.shop.utils.SpringUtils;
import com.xiaosecond.shop.vo.SysLoginLogVo;
import com.xiaosecond.shop.vo.SysOperationLogVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 */
@Slf4j
@Component
public class LogTaskFactory {



    public static TimerTask loginLog(final Long userId, final String ip , SysLoginLogMapper sysLoginLogMapper ) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    SysLoginLogVo loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, null, ip);
                    log.info("loginLog>>>>>>>>>{}" , loginLog.toString());
                    sysLoginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String username, final String msg, final String ip ,SysLoginLogMapper sysLoginLogMapper ) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLogVo loginLog = LogFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                    sysLoginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Long userId, final String ip , SysLoginLogMapper sysLoginLogMapper ) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLogVo loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null,ip);
                try {
                    sysLoginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final Long userId, final String bussinessName, final String clazzName, final String methodName, final String msg ,SysOperationLogMapper sysOperationLogMapper ) {
        return new TimerTask() {
            @Override
            public void run() {
                SysOperationLogVo operationLog = LogFactory.createOperationLog(
                        LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                    sysOperationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    log.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Long userId, final Exception exception , SysOperationLogMapper sysOperationLogMapper ) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = GlobalExceptionHandler.getExceptionMessage(exception);
                SysOperationLogVo operationLog = LogFactory.createOperationLog(
                        LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
                try {
                    sysOperationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    log.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
