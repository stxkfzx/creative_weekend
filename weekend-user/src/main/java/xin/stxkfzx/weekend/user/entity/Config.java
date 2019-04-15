package xin.stxkfzx.weekend.user.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Component
@ConfigurationProperties(prefix = "weekend")
public class Config {
    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
