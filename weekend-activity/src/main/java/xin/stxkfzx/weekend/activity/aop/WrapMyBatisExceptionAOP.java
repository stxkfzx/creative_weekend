package xin.stxkfzx.weekend.activity.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.common.exception.SqlException;

/**
 * 用于封装MyBatis异常
 *
 * @author fmy
 * @date 2019-04-19 11:33
 */
@Component
@Aspect
@Order(1)
public class WrapMyBatisExceptionAOP {

    private static final Logger log = LoggerFactory.getLogger(WrapMyBatisExceptionAOP.class);

    @Pointcut("execution(public * xin.stxkfzx.weekend.activity.mapper.*Mapper.* (..))")
    public void mybatisMethod() {

    }

    // @Around("mybatisMethod()")
    // public Object wrapException(ProceedingJoinPoint point) {
    //     try {
    //         return point.proceed();
    //     } catch (Throwable throwable) {
    //         log.debug("wrap MyBatis Exception to SqlException");
    //         throw new SqlException(throwable);
    //     }
    //
    // }

    @AfterThrowing(value = "mybatisMethod()", throwing = "e")
    public void wrapException(Throwable e) {
        log.debug("wrap MyBatis Exception to SqlException");
        throw new SqlException(e);
    }
}
