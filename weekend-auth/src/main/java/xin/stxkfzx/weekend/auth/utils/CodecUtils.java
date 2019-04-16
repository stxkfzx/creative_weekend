package xin.stxkfzx.weekend.auth.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/15
 */
public class CodecUtils {


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
    public static String generateSalt(Date date) {
        return StringUtils.replace(date.toString(), "-", "");
    }
}
