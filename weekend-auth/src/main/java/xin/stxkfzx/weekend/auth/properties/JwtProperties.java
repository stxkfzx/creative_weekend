package xin.stxkfzx.weekend.auth.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import xin.stxkfzx.weekend.auth.utils.RsaUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 获取中自定义的属性
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Primary
@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "weekend.jwt")
@Validated
public class JwtProperties {
    private final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * 为了解决其他模块扫描此模块的报错问题，此处先直接定义为 final类型
     */
    private final String secret = "weekend@Login(Auth}*^31)&kfzx%";

    private final String pubKeyPath = "G:\\rsa.pub";

    private final String priKeyPath = "G:\\rsa.pri";

    private Integer expire;

    private String cookieName;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    private int cookieMaxAge;

    @PostConstruct
    public void init() {
        try {
            // 首先判断公钥私钥是否存在，不存在则先生成公钥私钥
            File pubKey = new File(pubKeyPath);
            File priKey = new File(priKeyPath);

            if (!pubKey.exists() || !priKey.exists()) {
                // 创建公钥，私钥
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
                logger.info("创建公钥、私钥成功");
            }
            // 公钥私钥都存在
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);

        } catch (Exception e) {
            logger.error("创建公钥、私钥失败：{}", e.toString());
            e.printStackTrace();
            // TODO: 2019/4/13
            throw new RuntimeException();
        }
    }

    public String getSecret() {
        return secret;
    }


    public String getPubKeyPath() {
        return pubKeyPath;
    }


    public String getPriKeyPath() {
        return priKeyPath;
    }


    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public int getCookieMaxAge() {
        return cookieMaxAge;
    }

    public void setCookieMaxAge(int cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }
}
