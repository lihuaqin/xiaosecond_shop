package com.xiaosecond.shop.log;



import com.xiaosecond.shop.enums.LogSucceed;
import com.xiaosecond.shop.enums.LogType;
import com.xiaosecond.shop.vo.SysLoginLogVo;
import com.xiaosecond.shop.vo.SysOperationLogVo;



/**
 * 日志对象创建工厂
 *
 */
public class LogFactory {

    /**
     * 创建操作日志
     *
     */
    public static SysOperationLogVo createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        SysOperationLogVo operationLog = new SysOperationLogVo();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId.intValue());
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     */
    public static SysLoginLogVo createLoginLog(LogType logType, Long userId, String msg, String ip) {
        SysLoginLogVo loginLog = new SysLoginLogVo();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId.intValue());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        loginLog.setCreater(userId.intValue());
        loginLog.setUpdater(userId.intValue());
        return loginLog;
    }
}
