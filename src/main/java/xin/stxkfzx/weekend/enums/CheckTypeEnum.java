package xin.stxkfzx.weekend.enums;

/**
 * 检查参数类型
 *
 * @author fmy
 * @date 2019-04-17 20:25
 */
public enum CheckTypeEnum {
    /**
     * 不检查
     */
    NONE,

    /**
     * 检查不为空
     */
    NOT_NULL,

    /**
     * 使用Bean Validation进行校验
     */
    BEAN_VALIDATION
}
