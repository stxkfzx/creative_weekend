package xin.stxkfzx.weekend.common.exception;

import xin.stxkfzx.weekend.common.enums.ExceptionEnum;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
public class WeekendException extends RuntimeException {

	public WeekendException() {
	}

	public WeekendException(String message) {
		super(message);
	}

	public WeekendException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeekendException(Throwable cause) {
		super(cause);
	}

	public WeekendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WeekendException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
	}
}
