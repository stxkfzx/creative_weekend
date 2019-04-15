package xin.stxkfzx.weekend.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeekendOnlineApplication {

	private static final Logger logger = LoggerFactory.getLogger(WeekendOnlineApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WeekendOnlineApplication.class, args);
	}

}
