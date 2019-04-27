package xin.stxkfzx.weekend.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.CheckException;
import xin.stxkfzx.weekend.exception.NoPermissionException;
import xin.stxkfzx.weekend.exception.UnLoginException;
import xin.stxkfzx.weekend.exception.WeekendException;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 业务异常处理
     *
     * @param e WeekendException
     * @return ResponseEntity
     * @author fmy
     * @date 2019-04-12 17:20
     */
    @ExceptionHandler(WeekendException.class)
    public ResponseEntity<?> weekendExceptionHandle(WeekendException e) {
        if (e instanceof UnLoginException) {
            // 用户未登录异常
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionEnum.UN_LOGIN);
        } else if (e instanceof CheckException) {
            // 校验失败异常
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResultBean(Optional.ofNullable(e.getExceptionEnum())
                            .map(ExceptionEnum::getCode).orElse(ExceptionEnum.CHECK_FAIL.getCode()),
                            e.getLocalizedMessage()));
        } else if (e instanceof NoPermissionException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExceptionEnum.NO_PERMISSION);
        } else {
            // 业务逻辑异常属于正常状态，HTTP状态为200
            return ResponseEntity.ok(new ResultBean(Optional.ofNullable(e.getExceptionEnum())
                    .map(ExceptionEnum::getCode).orElse(ExceptionEnum.UNKNOWN_FAIL.getCode()), e.getLocalizedMessage()));
        }
    }

    /**
     * 全局异常处理
     *
     * @param e Exception
     * @return ResponseEntity
     * @author fmy
     * @date 2019-04-15 16:05
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandle(Exception e) {
        // 未知异常
        log.error("unknown error", e);
        // FIXME: 2019/4/12 未知异常HTTP状态码处理
        return ResponseEntity.ok(new ResultBean(ExceptionEnum.UNKNOWN_FAIL));
    }

    /**
     * 处理单属性校验异常
     *
     * @param e Exception
     * @return ResponseEntity
     * @author fmy
     * @date 2018-08-01 21:34
     */
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<?> validationExceptionHandle(Exception e) {
        log.warn(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultBean(ExceptionEnum.CHECK_FAIL.getCode(), e.getLocalizedMessage()));
    }

    /**
     * 处理Bean校验异常
     *
     * @param e Exception
     * @return ResponseEntity
     * @author fmy
     * @date 2018-08-01 21:37
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> bindExceptionHandle(Exception e) {
        log.warn(e.getLocalizedMessage());

        // 提取检验失败的字段和提示
        StringJoiner sj = new StringJoiner(";");

        MethodArgumentNotValidException bindException = (MethodArgumentNotValidException) e;
        List<FieldError> allErrors = bindException.getBindingResult().getFieldErrors();
        for (FieldError error :
                allErrors) {
            sj.add(String.format("%s:%s", error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultBean(ExceptionEnum.CHECK_FAIL.getCode(), sj.toString()));
    }

}

