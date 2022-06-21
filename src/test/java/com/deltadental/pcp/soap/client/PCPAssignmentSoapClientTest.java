package com.deltadental.pcp.soap.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;
import com.deltadental.platform.pcp.stub.ObjectFactory;
import com.deltadental.platform.pcp.stub.PcpProviderRequest;
import com.deltadental.platform.pcp.stub.PcpSearchRequest;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.Providers;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = { ApplicationConfig.class })
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties",
		"spring.config.enabled=false" })
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Testing PCP PCP Assignment Soap Client")
public class PCPAssignmentSoapClientTest {

	@Autowired
	private ObjectFactory objectFactory;

	@InjectMocks
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;

	@Test
	public void testProviders() {
		try {
			ReflectionTestUtils.setField(pcpAssignmentSoapClient, "objectFactory", objectFactory, ObjectFactory.class);
			pcpAssignmentSoapClient.setDefaultUri("http://aw-lx0558.deltadev.ent:24011/pcpservices/assignment?wsdl");
			Providers providers = new Providers();
			PcpSearchRequest request = new PcpSearchRequest();
			request.setAutoAssignment("ABC");
			request.setMemberId("01");
			request.setContractID("00112233");
			providers.setArg0(request);
			pcpAssignmentSoapClient.providers(providers);
		} catch (ServiceException exception) {
			Assertions.assertEquals(exception.getErrorCode().toString(),
					PCPSearchServiceErrors.PROVIDERS_ERROR.name());
		}
	}
	
	@Test
	public void testPcpValidate() {
		try {
			ReflectionTestUtils.setField(pcpAssignmentSoapClient, "objectFactory", objectFactory, ObjectFactory.class);
			pcpAssignmentSoapClient.setDefaultUri("http://aw-lx0558.deltadev.ent:24011/pcpservices/assignment?wsdl");
			PcpValidate pcpValidate = new PcpValidate();
			PcpProviderRequest request = new PcpProviderRequest();
			request.setContractId("123");
			request.setLookAheadDays("60");
			request.setMemberType("01");
			request.setProduct("00123");
			request.setProviderId("provider_1");
			pcpValidate.setArg0(request);
			pcpAssignmentSoapClient.pcpValidate(pcpValidate);
		} catch (ServiceException exception) {
			Assertions.assertEquals(exception.getErrorCode().toString(), 
					PCPSearchServiceErrors.PCPVALIDATE_ERROR.name());
		}
	}
}
