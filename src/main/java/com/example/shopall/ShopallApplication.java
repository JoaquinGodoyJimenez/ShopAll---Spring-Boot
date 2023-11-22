package com.example.shopall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopallApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopallApplication.class);

	public static void main(String[] args) {
		LOGGER.error("Prueba");
		SpringApplication.run(ShopallApplication.class, args);
	}

}
