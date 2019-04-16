package xin.stxkfzx.weekend.common.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

/**
 * 加密工具
 *
 * @author fmy
 * @date 2019-04-16 14:24
 */
public class EncryptionUtil {
    private EncryptionUtil() {
    }

    /**
     * 配置加密.配置时密文通过测试<code>EncryptionUtilTest</>中获取
     *
     * @param message 加密信息
     * @return 密文
     * @author fmy
     * @date 2019-04-16 14:34
     */
    public static String propertiesEncrypt(String message) {
        PooledPBEStringEncryptor encryptor =
                (PooledPBEStringEncryptor) ApplicationContextUtil.getBean("jasyptStringEncryptor");
        CheckUtils.notEmpty(message);
        String encrypt = encryptor.encrypt(message);
        return "$CW{" + encrypt + "}";
    }
}
