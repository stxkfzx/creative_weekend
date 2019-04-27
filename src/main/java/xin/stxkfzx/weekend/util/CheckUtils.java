package xin.stxkfzx.weekend.util;

import org.springframework.context.MessageSource;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.CheckException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
     * 校验条件
     *
     * @param condition 条件
     * @param errEnum   ExceptionEnum
     * @author fmy
     * @date 2019-04-18 12:01
     */
    public static void check(boolean condition, ExceptionEnum errEnum) {
        if (!condition) {
            fail(errEnum);
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
     * 校验不为空
     *
     * @param obj     校验对象
     * @param errEnum ExceptionEnum
     * @author fmy
     * @date 2019-04-18 12:02
     */
    public static void notNull(Object obj, ExceptionEnum errEnum) {
        if (obj == null) {
            fail(errEnum);
        }
    }

    /**
     * 校验不为空
     *
     * @param obj 校验对象
     * @author fmy
     * @date 2019-04-17 21:06
     */
    public static void notNull(Object obj) {
        if (obj == null) {
            fail("value.is.null");
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

    /**
     * 校验字符串不为空
     *
     * @param str     需要校验字符串
     * @param errEnum ExceptionEnum
     * @author fmy
     * @date 2019-04-18 12:03
     */
    public static void notEmpty(String str, ExceptionEnum errEnum) {
        if (str == null || str.trim().isEmpty()) {
            fail(errEnum);
        }
    }

    /**
     * 显式Bean校验
     *
     * @param bean 需要校验的bean
     * @author fmy
     * @date 2018-09-16 21:18
     */
    public static <T> void checkBean(T bean) {
        notNull(bean);

        StringBuilder sb = new StringBuilder();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        for (ConstraintViolation<T> item :
                violations) {
            sb.append(item.getPropertyPath().toString()).append(item.getMessage()).append(";");
        }

        String errMsg = sb.toString();
        if (!errMsg.isEmpty()) {
            throw new CheckException(errMsg);
        }
    }

    private static void fail(String msgKey, Object... args) {
        throw new CheckException(ApplicationContextUtil.getBean(MessageSource.class)
                .getMessage(msgKey, args, UserUtils.getLocale()));
    }

    private static void fail(ExceptionEnum errEnum) {
        throw new CheckException(errEnum);
    }

}
