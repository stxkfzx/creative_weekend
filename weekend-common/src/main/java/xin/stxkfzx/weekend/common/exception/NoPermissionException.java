package xin.stxkfzx.weekend.common.exception;

import xin.stxkfzx.weekend.common.enums.ExceptionEnum;

/**
 * 无权限操作异常
 *
 * @author fmy
 * @date 2019-04-18 20:02
 */
public class NoPermissionException extends WeekendException{

    public NoPermissionException() {
        super();
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoPermissionException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
