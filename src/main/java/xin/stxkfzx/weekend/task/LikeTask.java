package xin.stxkfzx.weekend.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import xin.stxkfzx.weekend.service.LikedService;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
public class LikeTask extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(LikeTask.class);

    @Autowired
    private LikedService likedService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        logger.info("LikeTask");
        // 将Redis里的点赞信息同步到数据库里
        likedService.transLikedFromRedis2DB();
        likedService.transLikedCountFromRedis2DB();
    }

}
