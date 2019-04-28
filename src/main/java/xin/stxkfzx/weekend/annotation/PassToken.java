package xin.stxkfzx.weekend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义可忽略token校验注解
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}