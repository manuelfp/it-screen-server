package com.devcodes.training.itscreenserver;

import com.devcodes.training.itscreenserver.configurations.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class ItScreenServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItScreenServerApplication.class, args);
	}

}
