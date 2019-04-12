package xin.stxkfzx.weekend.common.exception;

import xin.stxkfzx.weekend.common.enums.ExceptionEnum;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
public class WeekendException extends RuntimeException {
	private ExceptionEnum exceptionEnum;

	public WeekendException(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}

	public WeekendException(String message, ExceptionEnum exceptionEnum) {
		super(message);
		this.exceptionEnum = exceptionEnum;
	}

	public WeekendException(String message, Throwable cause, ExceptionEnum exceptionEnum) {
		super(message, cause);
		this.exceptionEnum = exceptionEnum;
	}

	public WeekendException(Throwable cause, ExceptionEnum exceptionEnum) {
		super(cause);
		this.exceptionEnum = exceptionEnum;
	}

	public WeekendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionEnum exceptionEnum) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.exceptionEnum = exceptionEnum;
	}

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

	public ExceptionEnum getExceptionEnum() {
		return exceptionEnum;
	}

	public void setExceptionEnum(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
}
