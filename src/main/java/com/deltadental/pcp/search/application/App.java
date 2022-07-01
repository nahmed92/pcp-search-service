package com.deltadental.pcp.search.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = { "com.deltadental.pcp.search.*","com.deltadental.platform.common.exception" })
@Slf4j
public class App {
	
	public static void main(String[] args) {
		log.info("Starting PCP-Search-Service");
		SpringApplication.run(App.class, args);
	}

}
