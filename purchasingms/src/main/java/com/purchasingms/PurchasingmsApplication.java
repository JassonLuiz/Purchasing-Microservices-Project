package com.purchasingms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class PurchasingmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchasingmsApplication.class, args);
	}

}
