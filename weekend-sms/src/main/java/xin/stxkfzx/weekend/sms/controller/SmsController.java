package xin.stxkfzx.weekend.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.sms.config.SmsProperties;
import xin.stxkfzx.weekend.sms.utils.SmsUtils;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@RestController
public class SmsController {
    private final StringRedisTemplate redisTemplate;
    private final SmsProperties smsProperties;
    private final SmsUtils smsUtils;


    @Autowired
    public SmsController(SmsProperties smsProperties, StringRedisTemplate redisTemplate,SmsUtils smsUtils) {
        this.smsProperties = smsProperties;
        this.redisTemplate = redisTemplate;
        this.smsUtils = smsUtils;
    }

    @GetMapping("/test")
    public void test() {
        System.out.println(smsUtils.sendCode("15034667029"));
        // System.out.println(smsUtils.sendCode("15364882057"));
    }
}
