package xin.stxkfzx.weekend.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.common.entity.ResultBean;
import xin.stxkfzx.weekend.common.enums.StatusEnum;

/**
 * 增强对ResponseEntity处理
 *
 * @author fmy
 * @date 2019-04-12 17:35
 */
@Component
@Aspect
public class ResponseEntityAOP {
    private static final Logger log = LoggerFactory.getLogger(ResponseEntityAOP.class);

    @Pointcut("execution(public org.springframework.http.ResponseEntity xin.stxkfzx.weekend.*.controller.*.*(..))")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()", returning = "entity")
    public ResponseEntity<?> processValue(JoinPoint point, ResponseEntity<?> entity) {
        log.info("Using {}", point.getSignature());
        Object body = entity.getBody();
        if (!(body instanceof ResultBean)) {
            log.debug("Encapsulate {} body with ResultBean,  which status is SUCCESS", point.getSignature());
            entity = updateField(entity, new ResultBean<>(StatusEnum.SUCCESS, body));
        }
        return entity;
    }

    private ResponseEntity<?> updateField(ResponseEntity<?> entity, ResultBean<?> body) {
        return new ResponseEntity<ResultBean<?>>(body, entity.getHeaders(), entity.getStatusCode());
    }
}
