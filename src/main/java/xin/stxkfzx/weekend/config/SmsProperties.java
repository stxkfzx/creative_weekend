package xin.stxkfzx.weekend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 读取配置信息
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "weekend.sms")
@Validated
public class SmsProperties {
    private String queryPath;
    private String accountSid;
    private String authToken;
    private String signName;
    private String keyPerfix;

    public String getQueryPath() {
        return queryPath;
    }

    public void setQueryPath(String queryPath) {
        this.queryPath = queryPath;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getKeyPerfix() {
        return keyPerfix;
    }

    public void setKeyPerfix(String keyPerfix) {
        this.keyPerfix = keyPerfix;
    }

    @Override
    public String toString() {
        return "SmsProperties{" +
                "queryPath='" + queryPath + '\'' +
                ", accountSid='" + accountSid + '\'' +
                ", authToken='" + authToken + '\'' +
                ", signName='" + signName + '\'' +
                ", keyPerfix='" + keyPerfix + '\'' +
                '}';
    }
}
