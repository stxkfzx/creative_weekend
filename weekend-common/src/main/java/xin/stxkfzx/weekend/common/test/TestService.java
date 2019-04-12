package xin.stxkfzx.weekend.common.test;

import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Service
public class TestService {

	public Object test(int i) {
		return null;
	}

	public Object test(String s) {
		throw new WeekendException(ExceptionEnum.TEST);
	}
}
