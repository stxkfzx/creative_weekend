package xin.stxkfzx.weekend.common.enums;

/**
 * 这个类用于存放定义好的异常，尽量写好注释
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
public enum ExceptionEnum {
	/**
	 * 测试异常
	 */
	TEST(400, "这是一个测试异常");
	private Integer code;
	private String msg;

	ExceptionEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	ExceptionEnum() {
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
