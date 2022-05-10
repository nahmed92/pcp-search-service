package com.deltadental.pcp.search.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.deltadental.pcp.search.*" })
public class PcpSearchServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PcpSearchServiceApplication.class, args);
	}

}
