package com.example.upgradedschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UpgradedScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpgradedScheduleApplication.class, args);
	}

}
