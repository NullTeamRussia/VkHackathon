package org.nullteam.hermitage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class HermitageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HermitageApplication.class, args);
	}
}
