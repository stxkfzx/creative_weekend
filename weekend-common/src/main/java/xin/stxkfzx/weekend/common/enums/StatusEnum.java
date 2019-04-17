package xin.stxkfzx.weekend.common.enums;

import org.springframework.context.MessageSource;
import xin.stxkfzx.weekend.common.util.ApplicationContextUtil;
import xin.stxkfzx.weekend.common.util.UserUtils;

import java.util.StringJoiner;

/**
 * 这个类用于正常业务逻辑状态，尽量写好注释
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
     * 被删除数据状态
     */
    DELETE(-1, "delete status"),

    /**
     * 审核状态，不可用作数据展示
     */
    REVIEW(0, "review status"),

    /**
     * 正常状态，可用作数据展示
     */
    NORMAL(1, "normal status");

    /**
     * 业务逻辑码
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
