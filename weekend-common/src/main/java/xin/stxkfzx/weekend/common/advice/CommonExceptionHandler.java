package xin.stxkfzx.weekend.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xin.stxkfzx.weekend.common.entity.ResultBean;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResultBean<?>> handleException(WeekendException e) {
		ExceptionEnum exceptionEnum = e.getExceptionEnum();
		return ResponseEntity.status(exceptionEnum.getCode()).body(new ResultBean<>(exceptionEnum.getMsg(), exceptionEnum.getCode()));
	}
}

