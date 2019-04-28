package xin.stxkfzx.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.util.SmsUtils;

/**
 * 发送短信相关
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    private final SmsUtils smsUtils;

    @Autowired
    public SmsController(SmsUtils smsUtils) {
        this.smsUtils = smsUtils;
    }

    /**
     * 发送验证码，有效期为十分钟
     *
     * @param phone 接收短信验证码手机号
     * @author ViterTian
     * @date 2019-04-15
     */
    @GetMapping("/code")
    public void sendCode(@RequestParam("phone") String phone) {
        smsUtils.sendCode(phone);
    }
}
