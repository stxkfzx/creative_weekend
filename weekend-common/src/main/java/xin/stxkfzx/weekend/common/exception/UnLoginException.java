package xin.stxkfzx.weekend.common.exception;

import xin.stxkfzx.weekend.common.enums.ExceptionEnum;

/**
 * 未登录异常
 *
 * @author fmy
 * @date 2019-04-10 20:27
 */
public class UnLoginException extends WeekendException {

    public UnLoginException() {
        super();
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    public UnLoginException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
