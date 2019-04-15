package xin.stxkfzx.weekend.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.sms.utils.SmsUtils;

/**
 * 发送短信相关
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@RestController
public class SmsController {
    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
    private final SmsUtils smsUtils;

    @Autowired
    public SmsController(SmsUtils smsUtils) {
        this.smsUtils = smsUtils;
    }

    @GetMapping("/code")
    public void test(@RequestParam("phone")String phone) {
        smsUtils.sendCode(phone);
        logger.info("【短信服务】发送短信成功。手机号码：{}", phone);
    }
}
