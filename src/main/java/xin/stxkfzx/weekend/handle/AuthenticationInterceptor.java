package xin.stxkfzx.weekend.handle;

import com.auth0.jwt.exceptions.JWTDecodeException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xin.stxkfzx.weekend.annotation.PassToken;
import xin.stxkfzx.weekend.config.JwtProperties;
import xin.stxkfzx.weekend.entity.UserBase;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.UnLoginException;
import xin.stxkfzx.weekend.service.impl.AuthServiceImpl;
import xin.stxkfzx.weekend.util.CheckUtils;
import xin.stxkfzx.weekend.util.JwtUtils;
import xin.stxkfzx.weekend.util.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 授权中心拦截器
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    @Autowired
    private AuthServiceImpl authServiceImpl;
    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有@PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        if (token == null) {
            throw new UnLoginException("无token，请重新登录");
        }
        // 获取 token 中的 auth id
        UserBase userBase;
        try {
            logger.info("获取到PublicKey()");
            userBase = JwtUtils.getUserBase(jwtProperties.getPublicKey(), token);
        } catch (JWTDecodeException j) {
            throw new UnLoginException("token无效，请重新登录");
        }catch (ExpiredJwtException e){
            throw new UnLoginException("token过期，请重新登录");
        }
        UserBase user = authServiceImpl.findUserById(userBase.getId());
        CheckUtils.notNull(user, ExceptionEnum.USER_NOT_EXIST);
        UserUtils.setUserInfo(user.getId());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
        UserUtils.clearUserInfo();
    }
}