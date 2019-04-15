package xin.stxkfzx.weekend.online;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xin.stxkfzx.weekend.online.mapper")

public class WeekendOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeekendOnlineApplication.class, args);
	}

}
