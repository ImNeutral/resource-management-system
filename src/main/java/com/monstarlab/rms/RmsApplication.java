package com.monstarlab.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RmsApplication.class, args);
	}
}