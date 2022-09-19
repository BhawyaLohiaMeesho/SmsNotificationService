package com.notification.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.notification.sms.dao.mysql")
@EnableElasticsearchRepositories(basePackages = "com.notification.sms.dao.elasticsearch")
public class SmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
		System.out.println("Application");
	}

}
