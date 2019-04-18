package xin.stxkfzx.weekend.activity.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.activity.annotation.CheckTypeEnum;
import xin.stxkfzx.weekend.activity.annotation.ParamCheck;
import xin.stxkfzx.weekend.common.util.CheckUtils;

/**
 * Service服务层入参检查
 *
 * @author fmy
 * @date 2019-04-17 19:31
 */
@Component
@Aspect
public class CheckServiceParameterAOP {
    private static final Logger log = LoggerFactory.getLogger(CheckServiceParameterAOP.class);

    @Pointcut("execution(public * xin.stxkfzx.weekend.activity.service..*(..))")
    public void checkPoint() {}



    @Before("checkPoint()")
    public void checkHandler(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        ParamCheck annotation = signature.getMethod().getAnnotation(ParamCheck.class);
        if (annotation == null || CheckTypeEnum.NONE.equals(annotation.checkType())) {
            return;
        }

        log.info("check type:{}, {} parameters", annotation.checkType(), point.getSignature());
        for (Object arg : point.getArgs()) {
            if (CheckTypeEnum.NOT_NULL.equals(annotation.checkType())) {
                CheckUtils.notNull(arg, "value.is.null");
            } else if (CheckTypeEnum.BEAN_VALIDATION.equals(annotation.checkType())) {
                CheckUtils.checkBean(arg);
            } else {
                throw new IllegalArgumentException(annotation.checkType() + "is unknown type");
            }
        }
        log.info("check success");
    }
}
