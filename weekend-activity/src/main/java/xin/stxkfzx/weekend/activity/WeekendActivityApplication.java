package xin.stxkfzx.weekend.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("xin.stxkfzx.weekend.activity.mapper")
@SpringBootApplication(scanBasePackages = "xin.stxkfzx.weekend")
public class WeekendActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekendActivityApplication.class, args);
    }

}
