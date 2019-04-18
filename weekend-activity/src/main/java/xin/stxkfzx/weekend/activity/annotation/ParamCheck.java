package xin.stxkfzx.weekend.activity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检查参数注解
 *
 * @author fmy
 * @date 2019-04-17 19:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {
    CheckTypeEnum checkType();

}
