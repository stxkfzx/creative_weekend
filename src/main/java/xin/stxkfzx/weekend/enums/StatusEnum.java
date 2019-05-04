package xin.stxkfzx.weekend.enums;

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
     * 聊天消息认证状态
     */
    CHAT_AUTH(3, "message auth status"),

    /**
     * 聊天文本消息
     */
    CHAT_TEXT(4, "text message"),

    /**
     * 加入状态
     */
    RECORD_JOIN(2, "join status"),

    /**
     * 退出状态
     */
    RECORD_EXIT(-2, "exit status"),

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
        this.msg = msg;
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
