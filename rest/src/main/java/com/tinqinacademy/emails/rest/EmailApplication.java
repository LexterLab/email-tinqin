package com.tinqinacademy.emails.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.tinqinacademy.emails"})
@EnableFeignClients(basePackages = {"com.tinqinacademy.emails"})
public class EmailApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}
}
