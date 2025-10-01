package com.dsfs.dsfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DsfsApplication {
	public static void main(String[] args) {
		SpringApplication.run(DsfsApplication.class, args);
	}
}
