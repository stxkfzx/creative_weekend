package xin.stxkfzx.weekend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("xin.stxkfzx.weekend.mapper")
@ComponentScan("xin.stxkfzx.weekend")
public class WeekendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeekendApplication.class, args);
    }
}
