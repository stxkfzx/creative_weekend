package xin.stxkfzx.weekend.auth.handle;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xin.stxkfzx.weekend.auth.config.PassToken;
import xin.stxkfzx.weekend.auth.config.UserLoginToken;
import xin.stxkfzx.weekend.auth.entity.UserBase;
import xin.stxkfzx.weekend.auth.properties.JwtProperties;
import xin.stxkfzx.weekend.auth.service.AuthService;
import xin.stxkfzx.weekend.auth.utils.JwtUtils;

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
@Order(199)
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    @Autowired
    private AuthService authService;
    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("WK_TOKEN");
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
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    // TODO: 2019/4/13 封装
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 auth id
                UserBase userBase;
                try {
                    logger.info("获取到PublicKey()");
                    userBase = JwtUtils.getUserBase(jwtProperties.getPublicKey(), token);
                } catch (JWTDecodeException j) {
                    // TODO: 2019/4/13 封装
                    throw new RuntimeException("401");
                }
                UserBase user = authService.findUserById(userBase.getId());
                if (user == null) {
                    // TODO: 2019/4/13 封装
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                return true;
            }
        }
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
    }
}