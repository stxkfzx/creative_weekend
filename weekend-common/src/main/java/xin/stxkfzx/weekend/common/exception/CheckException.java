package xin.stxkfzx.weekend.common.exception;

/**
 * 检查异常
 *
 * @author fmy
 * @date 2019-04-10 20:29
 */
public class CheckException extends RuntimeException {

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
}
