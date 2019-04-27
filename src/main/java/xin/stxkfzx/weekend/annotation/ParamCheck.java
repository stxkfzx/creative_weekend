package xin.stxkfzx.weekend.annotation;

import xin.stxkfzx.weekend.enums.CheckTypeEnum;

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
