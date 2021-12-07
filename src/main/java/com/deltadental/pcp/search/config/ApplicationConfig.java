package com.deltadental.pcp.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ApplicationConfig {

	@Value("${pcp.webservice.soap.uri}")
	private String pcpWSSoapUri;
}
