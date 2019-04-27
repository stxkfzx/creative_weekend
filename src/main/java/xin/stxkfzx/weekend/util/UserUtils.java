package xin.stxkfzx.weekend.util;

import org.slf4j.MDC;
import xin.stxkfzx.weekend.exception.UnLoginException;

import java.util.Locale;
import java.util.Optional;

/**
 * 用户工具类
 *
 * @author fmy
 * @date 2019-04-10 20:56
 */
public class UserUtils {
    private UserUtils() {
    }

    private static final ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Locale> tlLocale = ThreadLocal.withInitial(() -> Locale.ENGLISH);

    public static final String KEY_USER = "currentUser";
    public static final String KEY_LANG = "lang";

    /**
     * 获取当前用户ID
     *
     * @return UserId. 如果为空,则抛出<code>UnLoginException</code>异常
     * @author fmy
     * @date 2019-04-13 21:44
     */
    public static Integer getUserId() {
        return Optional.ofNullable(userThreadLocal.get()).orElseThrow(UnLoginException::new);
    }

    /**
     * 清空当前用户信息
     *
     * @author fmy
     * @date 2019-04-13 21:59
     */
    public static void clearUserInfo() {
        userThreadLocal.remove();
        MDC.remove(KEY_USER);
    }

    /**
     * 设置当前用户信息
     *
     * @param userId 当前用户Id
     * @author fmy
     * @date 2019-04-13 22:02
     */
    public static void setUserInfo(Integer userId) {
        userThreadLocal.set(userId);
        // 将用户信息放到日志
        MDC.put(KEY_USER, String.valueOf(userId));
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }
}
