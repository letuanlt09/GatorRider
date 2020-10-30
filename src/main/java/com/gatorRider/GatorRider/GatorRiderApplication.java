package com.gatorRider.GatorRider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GatorRiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatorRiderApplication.class, args);
	}

}
