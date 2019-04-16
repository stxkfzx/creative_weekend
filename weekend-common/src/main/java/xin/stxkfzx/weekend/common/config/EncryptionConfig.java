package xin.stxkfzx.weekend.common.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加密配置
 *
 * @author fmy
 * @date 2019-04-16 13:46
 */
@Configuration
public class EncryptionConfig {


    /**
     * 加密
     *
     * @author fmy
     * @date 2019-04-16 14:00
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("stx_creative_weekend");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.salt.NoOpIVGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    /**
     * 解密
     *
     * @author fmy
     * @date 2019-04-16 14:01
     */
    @Bean(name = "encryptablePropertyDetector")
    public EncryptablePropertyDetector encryptablePropertyDetector() {
        return new PropertyDetector();
    }

    private static class PropertyDetector implements EncryptablePropertyDetector {
        private static final String PREFIX = "$CW{";
        private static final String SUFFIX = "}";

        @Override
        public boolean isEncrypted(String s) {
            if (s != null) {
                return s.trim().startsWith(PREFIX) && s.trim().endsWith(SUFFIX);
            }
            return false;
        }

        @Override
        public String unwrapEncryptedValue(String s) {
            return s.substring(PREFIX.length(), s.length()-SUFFIX.length());
        }
    }
}
