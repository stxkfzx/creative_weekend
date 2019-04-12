package xin.stxkfzx.weekend.common.entity;

import java.io.Serializable;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
public class ResultBean<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	private String msg = "success";

	private Integer code = SUCCESS;

	private T data;

	public ResultBean() {
		super();
	}

	public ResultBean(T data) {
		super();
		this.data = data;

	}

	public ResultBean(String msg, Integer code, T data) {

		super();
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public ResultBean(String msg, Integer code) {

		super();
		this.msg = msg;
		this.code = code;
	}

	public ResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static int getNoLogin() {
		return NO_LOGIN;
	}

	public static int getSUCCESS() {
		return SUCCESS;
	}

	public static int getFAIL() {
		return FAIL;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
