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
     * 验证码过期
     */
    INVALID_VERIFY_CODE(400, "无效验证码"),

    /**
     * 新增用户异常
     */
    USER_SAVE_ERROR(500, "新增用户失败"),

    /**
     * 用户实名认证异常
     */
    AUTH_USER_SAVE_ERROR(500, "用户实名认证异常"),

    /**
     * 登录异常
     */
    INVALID_USER(400, "用户名或密码错误"),

    /**
     * 未登录状态
     */
    DUPLICATE_USER_NAME(500, "用户名重复"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(500, "用户不存在"),

    /**
     * 未登录状态
     */
    UN_LOGIN(-100, "user.not.login"),


    /**
     * 校验失败状态
     */
    CHECK_FAIL(-200, "field.invalid"),

    /**
     * 未知错误状态
     */
    UNKNOWN_FAIL(0, "unknown.error");
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
