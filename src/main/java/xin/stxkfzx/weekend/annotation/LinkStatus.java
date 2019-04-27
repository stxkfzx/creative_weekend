package xin.stxkfzx.weekend.annotation;

import xin.stxkfzx.weekend.enums.LinkTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 关联状态，用于标记需要服务之间关联的状态。
 *
 * @author fmy
 * @date 2019-04-26 23:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkStatus {
    LinkTypeEnum type();
}
