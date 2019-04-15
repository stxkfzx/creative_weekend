package xin.stxkfzx.weekend.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import xin.stxkfzx.weekend.common.enums.StatusEnum;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultBean<T> implements Serializable {
    private T body;
    private Integer code;
    private String msg;

	public ResultBean() {
		super();
	}

    public ResultBean(StatusEnum status) {
        this(status.getCode(), status.getMsg());
    }

    public ResultBean(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultBean(StatusEnum status, T body) {
        this(status);
        this.body = body;
    }

    public ResultBean(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResultBean.class.getSimpleName() + "[", "]")
                .add("body=" + body)
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .toString();
    }
}
