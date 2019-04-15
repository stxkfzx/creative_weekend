package xin.stxkfzx.weekend.common.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xin.stxkfzx.weekend.common.entity.ResultBean;
import xin.stxkfzx.weekend.common.enums.StatusEnum;
import xin.stxkfzx.weekend.common.exception.CheckException;
import xin.stxkfzx.weekend.common.exception.UnLoginException;
import xin.stxkfzx.weekend.common.exception.WeekendException;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 全局异常处理
     *
     * @param e Exception
     * @return ResponseEntity
     * @author fmy
     * @date 2019-04-12 17:20
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception e) {

        if (e instanceof WeekendException) {
            // 业务逻辑异常属于正常状态，HTTP状态为200
            log.warn(e.getLocalizedMessage());
            return ResponseEntity.ok(new ResultBean(StatusEnum.SUCCESS.getCode(), e.getLocalizedMessage()));
        } else if (e instanceof CheckException || e instanceof IllegalAccessException) {
            // 校验失败异常
            log.warn(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResultBean(StatusEnum.CHECK_FAIL.getCode(), e.getLocalizedMessage()));
        } else if (e instanceof UnLoginException) {
            // 用户未登录异常
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(StatusEnum.UN_LOGIN);
        } else {
            // TODO: 2019/4/12 后续添加异常处理
            // 未知异常
            log.error("unknown error", e);
            // FIXME: 2019/4/12 未知异常HTTP状态码处理
            return ResponseEntity.ok(new ResultBean(StatusEnum.UNKNOWN_FAIL));
        }

    }

}

