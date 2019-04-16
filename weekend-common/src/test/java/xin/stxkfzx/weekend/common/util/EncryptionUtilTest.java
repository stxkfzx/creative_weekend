package xin.stxkfzx.weekend.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import xin.stxkfzx.weekend.common.WeekendCommonApplicationTests;

import static org.junit.Assert.*;

/**
 * @author fmy
 * @date 2019-04-16 14:35
 */
public class EncryptionUtilTest extends WeekendCommonApplicationTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void propertiesEncrypt() {
        String message = "jdbc:mysql://119.23.208.165:3306/creative_weekend?useSSL=false&useUnicode=true&characterEncoding=utf8";
        String pd = EncryptionUtil.propertiesEncrypt(message);
        CheckUtils.notEmpty(pd);
        System.out.println(pd);

    }
}