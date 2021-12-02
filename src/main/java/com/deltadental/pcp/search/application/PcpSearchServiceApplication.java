package com.deltadental.pcp.search.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.deltadental.pcp.search.controller.PCPSearchServiceController;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "com.deltadental.pcp.search.*" })
@EnableSwagger2
@Slf4j
public class PcpSearchServiceApplication {

	@Value("${enable.access.token.filter}")
	String accessTokenFilterEnableFlag;
	
	public static void main(String[] args) {
		SpringApplication.run(PcpSearchServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		log.info("enable.access.token.filter:{}", accessTokenFilterEnableFlag);
	}
	
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(PCPSearchServiceController.class.getPackageName()))
	            .build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
        String description = "Delta Dental Ins -  PCP Search Service";
        return new ApiInfoBuilder()
                .title("REST API")
                .description(description)
                .version("1.0")
                .build();
    }

//	@Bean
//	public SaajSoapMessageFactory messageFactory() {
//	    SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
//	    messageFactory.setSoapVersion(SoapVersion.SOAP_12);
//	    return messageFactory;
//	}
//	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/pcp-search-service-error-messages");
		return messageSource;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Primary
	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
	    objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
	    objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    return objectMapper;
	}

}
