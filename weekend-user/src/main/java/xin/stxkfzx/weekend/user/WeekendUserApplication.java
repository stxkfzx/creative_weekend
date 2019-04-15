package xin.stxkfzx.weekend.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@SpringBootApplication
@MapperScan("xin.stxkfzx.weekend.user.mapper")
@ComponentScan("xin.stxkfzx.weekend")
public class WeekendUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeekendUserApplication.class, args);
    }
}
