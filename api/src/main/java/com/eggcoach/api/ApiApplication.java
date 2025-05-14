package com.eggcoach.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
	"com.eggcoach.api",
	"com.eggcoach.core.security",
	"com.eggcoach.core.domain",
	"com.eggcoach.infrastructure"
})

@EntityScan(basePackages = {
	"com.eggcoach.infrastructure"
})

@EnableJpaRepositories(
	basePackages = "com.eggcoach.infrastructure"
)  // 리포지토리 위치 지정
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
