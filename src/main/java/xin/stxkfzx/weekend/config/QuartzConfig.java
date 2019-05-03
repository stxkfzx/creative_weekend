package xin.stxkfzx.weekend.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.stxkfzx.weekend.task.LikeTask;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail() {
        return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                // TODO: 2019/5/2  改为24个小时执行一次
                .withIntervalInMinutes(10)
                // .withIntervalInHours(24)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .startAt(DateBuilder.evenSecondDate(new Date()))
                .build();
    }
}