package xin.stxkfzx.weekend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 创意周末后台项目入口
 *
 * @author CreativeWeekendTeam
 * @version V1.0
 * @date 2019/4/13
 */
@SpringBootApplication
@EnableCaching
@MapperScan("xin.stxkfzx.weekend.mapper")
@EnableScheduling
public class WeekendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeekendApplication.class, args);
    }
}
