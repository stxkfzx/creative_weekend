package xin.stxkfzx.weekend.common.util;

import java.util.Locale;

/**
 * 用户工具类
 *
 * @author fmy
 * @date 2019-04-10 20:56
 */
public class UserUtils {
    private UserUtils() {}

    private static final ThreadLocal<Locale> tlLocale = ThreadLocal.withInitial(() -> Locale.ENGLISH);

    public static Locale getLocale() {
        return tlLocale.get();
    }
}
