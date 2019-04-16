package xin.stxkfzx.weekend.auth.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/15
 */
public class CodecUtils {
    private static final Logger logger = LoggerFactory.getLogger(CodecUtils.class);

    public static String md5Hex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }

    /**
     * 通过用户的注册时间生成盐值
     *
     * @return 生成的盐值
     */
    public static String generateSalt(String data) {

        String salt = StringUtils.reverse(data);
        logger.info("生成盐：{}", salt);
        return salt;
    }
}
