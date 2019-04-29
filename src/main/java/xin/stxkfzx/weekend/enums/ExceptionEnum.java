package xin.stxkfzx.weekend.enums;

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
     * 用户名重复
     */
    DUPLICATE_USER_NAME(500, "用户名重复"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(500, "用户不存在"),

    /**
     * 用户认证失败
     */
    USER_AUTH_ERROR(500, "认证用户失败"),

    /**
     * 没有权限进行操作
     */
    NO_PERMISSION(501, "没有权限操作"),

    /**
     * 无效文件类型
     */
    INVALID_FILE_TYPE(400, "无效文件类型"),
    /**
     * 文件上传失败
     */
    UPLOAD_FILE_ERROR(500, "文件上传失败"),
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
    UNKNOWN_FAIL(0, "unknown.error"),

    /**
     * 活动不存在
     */
    ACTIVATE_NOT_EXIST(600, "活动不存在"),

    /**
     * 活动正在审核
     */
    ACTIVITY_IS_REVIEW(601, "活动正在审核中"),

    /**
     * 活动创建失败
     */
    ACTIVATE_CREATE_FAIL(602, "活动创建失败"),

    /**
     * 文件类型错误
     */
    FILE_TYPE_ERROR(500,"文件类型错误")

    ;


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
