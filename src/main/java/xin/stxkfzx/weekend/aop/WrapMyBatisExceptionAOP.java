package xin.stxkfzx.weekend.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.exception.SqlException;

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

    @Pointcut("execution(public * xin.stxkfzx.weekend.mapper.*Mapper.* (..))")
    public void mybatisMethod() {

    }

    @AfterThrowing(value = "mybatisMethod()", throwing = "e")
    public void wrapException(Throwable e) {
        log.debug("wrap MyBatis Exception to SqlException");
        throw new SqlException(e);
    }
}
