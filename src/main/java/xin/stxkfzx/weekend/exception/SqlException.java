package xin.stxkfzx.weekend.exception;

import xin.stxkfzx.weekend.enums.ExceptionEnum;

/**
 * 用于转换MyBatis异常
 *
 * @author fmy
 * @date 2019-04-19 11:31
 */
public class SqlException extends WeekendException {
    public SqlException(String message) {
        super(message);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlException(Throwable cause) {
        super(cause);
    }

    public SqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SqlException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
