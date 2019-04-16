package xin.stxkfzx.weekend.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@SpringBootApplication
@MapperScan("xin.stxkfzx.weekend.auth.mapper")
@ComponentScan("xin.stxkfzx.weekend.*")
public class WeekendAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeekendAuthApplication.class, args);
    }
}
