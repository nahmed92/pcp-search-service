package com.deltadental.pcp.search.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.deltadental.pcp.search.*","com.deltadental.platform.common.exception" })
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
