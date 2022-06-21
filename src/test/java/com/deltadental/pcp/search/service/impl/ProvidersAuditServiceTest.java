package com.deltadental.pcp.search.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.deltadental.pcp.search.config.ApplicationConfig;
import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.repos.ProvidersAuditRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties", "spring.config.enabled=false"})
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Testing Providers Audit Service Impl")
public class ProvidersAuditServiceTest {
	
	@Mock
	private ProvidersAuditRepo providersAuditRepo;

	@Autowired
	private ObjectMapper mapper;

	@InjectMocks
	private ProvidersAuditService providersAuditService;
	
	@Test
	public void testProviderSave() {	
		ReflectionTestUtils.setField(providersAuditService, "mapper", mapper, ObjectMapper.class);	
		ProvidersRequest providersRequest = ProvidersRequest.builder()
				.address(new Address())
				.autoAssignment("4321")
				.contractId("prod01")
				.memberId("member01")
				.pcpEffectiveDate("02-04-2022")
				.sourceSystem("source_01")
				.build();
		ProvidersResponse providersResponse = new ProvidersResponse();
		providersAuditService.save(providersRequest,providersResponse);
	}
}
