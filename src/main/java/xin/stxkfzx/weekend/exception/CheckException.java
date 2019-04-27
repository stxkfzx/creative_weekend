package xin.stxkfzx.weekend.exception;

import xin.stxkfzx.weekend.enums.ExceptionEnum;

/**
 * 检查异常
 *
 * @author fmy
 * @date 2019-04-10 20:29
 */
public class CheckException extends WeekendException {

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
