package com.xiaosecond.shop.common;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * 响应对象
 * @author
 * @param <T> 数据类型
 */
public class Response<T> {

    // 返回代码
    @ApiModelProperty(notes = "响应码 0：成功 1：失败")
    private Integer code;

    // 返回信息
    @ApiModelProperty(notes = "响应消息")
    private String msg;

    // 附加描述
    @ApiModelProperty(notes = "附加描述")
    private String desc;

    // 返回时间戳
    @ApiModelProperty(notes = "时间戳")
    private long timestamp = System.currentTimeMillis();

    // 数据对象
    @ApiModelProperty("返回数据")
    private T data;

    // 数据对象
    @ApiModelProperty("返回数据")
    private boolean success;

    /**
     * 构造方法
     */
    public Response() {
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     */
    public Response(Integer code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     * @param data 数据对象
     */
    public Response(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     * @param msg  返回信息
     */
    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     * @param msg  返回信息
     * @param data 数据对象
     */
    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     * @param msg  返回信息
     * @param desc 附加描述
     */
    public Response(Integer code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    /**
     * 构造方法
     *
     * @param code 返回代码
     * @param msg  返回信息
     * @param desc 附加描述
     * @param data 数据对象
     */
    public Response(Integer code, String msg, String desc, T data) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
        this.data = data;
    }

    /**
     * 是否为成功响应
     *
     * @return boolean 是否
     */
    public boolean success() {
        return this.code != null && this.code.equals(RetCode.SUCCESS.getCode());
    }

    public Integer getCode() {
        return code;
    }

    public Response setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Response setDesc(String description) {
        this.desc = description;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Response setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response setData(T data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }

}
