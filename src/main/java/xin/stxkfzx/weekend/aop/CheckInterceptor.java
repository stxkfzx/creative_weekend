package xin.stxkfzx.weekend.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.CheckException;
import xin.stxkfzx.weekend.handle.AuthenticationInterceptor;
import xin.stxkfzx.weekend.service.impl.AuthServiceImpl;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/17
 */
@Aspect
@Order(100)
@Component
public class CheckInterceptor {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    private final AuthServiceImpl authServiceImpl;

    public CheckInterceptor(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @Pointcut("@annotation(xin.stxkfzx.weekend.annotation.CheckUserIsExist)")
    public void checkUser() {
    }

    @Before("checkUser()")
    public void check(JoinPoint point) {
        logger.info("开始执行CheckUserIsExist注解部分");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                logger.info("检测到此方法有User对象的实例,判断此用户是否存在");
                if (authServiceImpl.findUserById(((User) arg).getTbId()) == null) {
                    throw new CheckException(ExceptionEnum.USER_NOT_EXIST);
                }
            }
        }
    }
}
