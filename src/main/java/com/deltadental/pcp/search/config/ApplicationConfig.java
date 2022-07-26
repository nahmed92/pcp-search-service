package com.deltadental.pcp.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.pcp.stub.ObjectFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Data
@Slf4j
public class ApplicationConfig {

	@Value("${pcp.webservice.soap.uri}")
	private String pcpWSSoapUri;
	
	private static final String WS_STUB_PACKAGE = "com.deltadental.platform.pcp.stub";

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(WS_STUB_PACKAGE);
		return jaxb2Marshaller;
	}

	@Bean(name = "pcpAssignmentSoapClient")
    public PCPAssignmentSoapClient pcpAssignmentSoapClient(Jaxb2Marshaller marshaller) {
		PCPAssignmentSoapClient pcpAssignmentSoapClient = new PCPAssignmentSoapClient();
		pcpAssignmentSoapClient.setDefaultUri(pcpWSSoapUri);
		pcpAssignmentSoapClient.setMarshaller(marshaller);
		pcpAssignmentSoapClient.setUnmarshaller(marshaller);
        return pcpAssignmentSoapClient;
    }


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
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

	@Bean
	ObjectFactory objectFactory() {
		return new ObjectFactory();
	}
}
