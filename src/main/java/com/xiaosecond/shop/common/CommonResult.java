package com.xiaosecond.shop.common;


import lombok.Data;
import java.io.Serializable;

@Data
public class CommonResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8573855566812280377L;

	/**
     * 0为返回正常， 其它code均为请求错误
     */
    private Integer code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String msg;


	public CommonResult() {
        this.code = RetCode.SUCCESS.getCode();
        this.msg = "";
    }

    public CommonResult(Object data) {
        this();
        this.data = data;
    }

    public CommonResult(String msg) {
        this.code = RetCode.SUCCESS.getCode();
        this.msg = msg;
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public CommonResult(Integer code, String msg , Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code + ";" + this.msg;
	}

    
}
