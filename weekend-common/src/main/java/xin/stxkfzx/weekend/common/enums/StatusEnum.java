package xin.stxkfzx.weekend.common.enums;

import org.springframework.context.MessageSource;
import xin.stxkfzx.weekend.common.util.ApplicationContextUtil;
import xin.stxkfzx.weekend.common.util.UserUtils;

import java.util.StringJoiner;

/**
 * 这个类用于存放业务逻辑状态，尽量写好注释
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
public enum StatusEnum {

    /**
     * 成功状态
     */
    SUCCESS(100, "success"),

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

    /**
     * 业务逻辑码
     * code >= 100  成功状态
     * code < 100   异常状态
     */
    private Integer code;
    private String msg;


    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = getI18nMessage(msg);
    }

    private String getI18nMessage(String msgKey) {
        return ApplicationContextUtil.getBean(MessageSource.class)
                .getMessage(msgKey, null, UserUtils.getLocale());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StatusEnum.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .toString();
    }
}
