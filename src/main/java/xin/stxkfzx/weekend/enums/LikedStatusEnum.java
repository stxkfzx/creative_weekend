package xin.stxkfzx.weekend.enums;

/**
 * 用户点赞的状态
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/1
 */
public enum LikedStatusEnum {
    /**
     * 点赞
     */
    LIKE(1, "点赞"),
    /**
     * 取消点赞/未点赞
     */
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}