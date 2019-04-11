package xin.stxkfzx.weekend.common.exception;

/**
 * 未登录异常
 *
 * @author fmy
 * @date 2019-04-10 20:27
 */
public class UnLoginException extends RuntimeException {

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
}
