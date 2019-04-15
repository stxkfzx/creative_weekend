package xin.stxkfzx.weekend.activity.service;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.activity.WeekendActivityApplicationTests;
import xin.stxkfzx.weekend.activity.entity.Activity;

import static org.junit.Assert.*;

/**
 * @author fmy
 * @date 2019-04-15 11:05
 */
public class ActivityServiceTest extends WeekendActivityApplicationTests {
    @Autowired
    private ActivityService service;

    @Test
    public void selectByUserIdWithPage() {
        int page = -1;
        int pageSize = 2;
        PageInfo<Activity> pageInfo = service.selectByUserIdWithPage(page, pageSize, 1);
        assertEquals(pageSize, pageInfo.getList().size());

        page = 0;
        pageInfo = service.selectByUserIdWithPage(page, pageSize, 1);
        assertEquals(pageSize, pageInfo.getList().size());
        assertTrue(!pageInfo.isHasPreviousPage());

        page = 1;
        pageInfo = service.selectByUserIdWithPage(page, pageSize, 1);
        assertEquals(pageSize, pageInfo.getList().size());
        assertTrue(!pageInfo.isHasPreviousPage());

        page = 1000000;
        pageInfo = service.selectByUserIdWithPage(page, pageSize, 1);
        assertEquals(0, pageInfo.getList().size());

        pageSize = -1;
        page = 0;
        pageInfo = service.selectByUserIdWithPage(page, pageSize, 1);
        assertEquals(0, pageInfo.getList().size());
    }
}