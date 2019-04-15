package xin.stxkfzx.weekend.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("xin.stxkfzx.weekend.activity.mapper")
@ComponentScan(value = {"xin.stxkfzx.weekend.common.*", "xin.stxkfzx.weekend.activity.*"})
@SpringBootApplication
public class WeekendActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekendActivityApplication.class, args);
    }

}
