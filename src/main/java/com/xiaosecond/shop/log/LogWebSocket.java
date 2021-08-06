package com.xiaosecond.shop.log;

import com.alibaba.fastjson.JSONObject;
import com.xiaosecond.shop.excpetion.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Slf4j
@ServerEndpoint("/LogWebSocket")
@Component
public class LogWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static CopyOnWriteArraySet<LogWebSocket> webSocketSet = new CopyOnWriteArraySet<LogWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 过滤条件 设备id --null或空串,查询全部
     */
    private String deviceId;

    /**
     * 过滤条件 日志级别  TRACE  DEBUG  INFO  WARN  ERROR --null或空串,查询全部
     */
    private String logLevel;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为{}————{}" , getOnlineCount() , new Timestamp(System.currentTimeMillis()).toString());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if(!StringUtils.isEmpty(message)){
                JSONObject mesObj = JSONObject.parseObject(message);
                this.deviceId = mesObj.getString("deviceId");
                this.logLevel = mesObj.getString("logLevel");
            }
        }catch (Exception e){
            log.error(GlobalExceptionHandler.getExceptionMessage(e));
        }
        log.info("来自客户端 {} 的消息:" , session.getId() , message);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message , String levelStr) throws IOException{
        if(!StringUtils.isEmpty(this.deviceId) && message.indexOf("- deviceId:[" + this.deviceId + "] -") == -1){  //通过deviceId过滤不推送信息
            return ;
        }
        if(!StringUtils.isEmpty(this.logLevel) && !this.logLevel.equals(levelStr)){  //通过logLevel过滤不推送信息
            return ;
        }
        if(this.session != null ){
            this.session.getBasicRemote().sendText(message);  //同步发送消息
            //this.session.getAsyncRemote().sendText(message); //异步发送消息
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        LogWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        LogWebSocket.onlineCount--;
    }
}
