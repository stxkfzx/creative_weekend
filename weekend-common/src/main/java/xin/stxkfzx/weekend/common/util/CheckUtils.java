package xin.stxkfzx.weekend.common.util;

import org.springframework.context.MessageSource;
import xin.stxkfzx.weekend.common.exception.CheckException;

/**
 * 校验工具类
 *
 * @author fmy
 * @date 2019-04-10 20:24
 */
public class CheckUtils {
    private CheckUtils() {
    }

    /**
     * 校验条件
     *
     * @param condition 条件
     * @param msgKey    错误信息(与国际化参数一致)
     * @param args      错误参数
     * @author fmy
     * @date 2019-04-10 21:22
     */
    public static void check(boolean condition, String msgKey, Object... args) {
        if (!condition) {
            fail(msgKey, args);
        }
    }

    /**
     * 校验不为空
     *
     * @param obj    校验对象
     * @param msgKey 错误信息(与国际化参数一致)
     * @param args   错误参数
     * @author fmy
     * @date 2019-04-10 21:43
     */
    public static void notNull(Object obj, String msgKey, Object... args) {
        if (obj == null) {
            fail(msgKey, args);
        }
    }

    /**
     * 校验字符串不为空
     *
     * @param str 需要校验字符串
     * @author fmy
     * @date 2019-04-11 8:21
     */
    public static void notEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            fail("value.is.null");
        }
    }

    private static void fail(String msgKey, Object... args) {
        throw new CheckException(ApplicationContextUtil.getBean(MessageSource.class)
                .getMessage(msgKey, args, UserUtils.getLocale()));
    }

}
