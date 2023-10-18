package com.compassuol.sp.challenge.msfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsFeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFeedbackApplication.class, args);
	}

}
