package xin.stxkfzx.weekend.user.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.auth.handle.AuthenticationInterceptor;
import xin.stxkfzx.weekend.auth.service.AuthService;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.CheckException;
import xin.stxkfzx.weekend.user.entity.User;

import java.lang.reflect.Field;

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
    private final AuthService authService;

    public CheckInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Pointcut("@annotation(xin.stxkfzx.weekend.auth.config.CheckUserIsExist)")
    public void checkUser() {
    }

    @Before("checkUser()")
    public void check(JoinPoint point) {
        logger.info("开始执行CheckUserIsExist注解部分");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                logger.info("检测到此方法有User对象的实例,判断此用户是否存在");
                if (authService.findUserById(((User) arg).getTbId()) == null) {
                    logger.error("用户{}不存在",arg);
                    throw new CheckException(ExceptionEnum.USER_NOT_EXIST);
                }
            }
        }
    }
}
