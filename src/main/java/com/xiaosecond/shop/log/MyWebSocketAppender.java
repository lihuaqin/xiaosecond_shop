package com.xiaosecond.shop.log;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


/**
 * 自定义实现logback的输出源
 *
 *
 */
@Getter
@Setter
public class MyWebSocketAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    Layout<ILoggingEvent> layout;

    //自定义配置
    String printString;

    @Override
    public void start(){
        //这里可以做些初始化判断 比如layout不能为null ,
        if(layout == null) {
            addWarn("Layout was not defined");
        }
        //或者写入数据库 或者redis时 初始化连接等等
        super.start();
    }

    @Override
    public void stop()
    {
        //释放相关资源，如数据库连接，redis线程池等等
        System.out.println("logback-stop方法被调用");
        if(!isStarted()) {
            return;
        }
        super.stop();
    }

    @SneakyThrows
    @Override
    public void append(ILoggingEvent event) {
        if (event == null || !isStarted()){
            return;
        }
        // 此处自定义实现输出
        // 获取输出值：event.getFormattedMessage()
        // System.out.print(event.getFormattedMessage());
        // 格式化输出
//        System.out.print("append>>>>>>" + printString + "：" + layout.doLayout(event));
        for (LogWebSocket logWebSocket: LogWebSocket.webSocketSet) {
            logWebSocket.sendMessage(layout.doLayout(event) , event.getLevel().levelStr);
        }

    }
}

