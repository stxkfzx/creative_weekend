package xin.stxkfzx.weekend.common.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.common.entity.ResultBean;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@RestController
public class TestController {
	private final TestService testService;

	public TestController(TestService testService) {
		this.testService = testService;
	}

	@GetMapping("/success")
	public ResponseEntity<ResultBean<?>> success(){
		return ResponseEntity.ok(new ResultBean<>(testService.test(111)));
	}

	@GetMapping("/test")
	public ResponseEntity<ResultBean<?>> test(){
		return ResponseEntity.ok(new ResultBean<>(testService.test("111")));
	}
}
