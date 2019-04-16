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
public enum SuccessStatusEnum {

    /**
     * 成功状态
     */
    SUCCESS(100, "success");

    /**
     * 业务逻辑码
     */
    private Integer code;
    private String msg;


    SuccessStatusEnum(Integer code, String msg) {
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
        return new StringJoiner(", ", SuccessStatusEnum.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .toString();
    }
}
