package com.kbh.elk.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ELKApplication {
	public static void main(String[] args) {
		SpringApplication.run(ELKApplication.class, args);
	}
}
