package xin.stxkfzx.weekend.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import xin.stxkfzx.weekend.WeekendApplicationTests;
import xin.stxkfzx.weekend.exception.CheckException;

import static org.junit.Assert.assertNotNull;

/**
 * @author fmy
 * @date 2019-04-10 21:54
 */
public class CheckUtilsTest extends WeekendApplicationTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void check() {
        exception.expect(CheckException.class);
        boolean condition = "1".length() > 5;
        CheckUtils.check(condition, "field.invalid");
    }

    @Test
    public void notNull() {
        exception.expect(CheckException.class);
        CheckUtils.notNull(null, "value.is.null");

        CheckException obj = new CheckException();
        assertNotNull(obj);
        CheckUtils.notNull(obj, "value.is.null");
    }

    @Test
    public void notEmpty() {
        String str = null;
        exception.expect(CheckException.class);
        CheckUtils.notEmpty(str);

        str = "";
        exception.expect(CheckException.class);
        CheckUtils.notEmpty(str);

        str = "            ";
        exception.expect(CheckException.class);
        CheckUtils.notEmpty(str);

        str = "          sdada  ";
        CheckUtils.notEmpty(str);

        str = "test";
        CheckUtils.notEmpty(str);
    }
}