package com.deltadental.pcp.search.service.impl;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deltadental.pcp.search.config.ApplicationConfig;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties", "spring.config.enabled=false"})
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Testing PCP Search Service Impl")
public class PCPSearchServiceImplTest {

	private PCPSearchService pcpSearchServiceImpl;  
	 
	@Autowired
	private ObjectMapper mapper;
	
	@Mock 
	private PCPAssignmentSoapClient pcpAssignmentSoapClientMock;
	
	@Mock 
	private PCPSearchRequestTransformer pcpSearchRequestTransformerMock;
	
	@Mock 
	private PCPSearchResponseTransformer pcpSearchResponseTransformerMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		pcpSearchServiceImpl = new PCPSearchService();
		pcpSearchServiceImpl.setPcpAssignmentSoapClient(pcpAssignmentSoapClientMock);
		pcpSearchServiceImpl.setPcpSearchRequestTransformer(pcpSearchRequestTransformerMock);
		pcpSearchServiceImpl.setPcpSearchResponseTransformer(pcpSearchResponseTransformerMock);
	}

	//@Test //FIXME:
	@DisplayName("Testing Providers success")
	void testProviders() throws Exception  {
		ProvidersRequest providersRequest = mapper
                .readerFor(ProvidersRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providersRequest.json"));
		PCPAssignmentResponse expected = mapper
                .readerFor(PCPAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providersResponse.json"));
		
		PcpAssignmentResponse stubPcpAssignmentResponse = mapper
                .readerFor(PcpAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providersResponse.json"));
 		
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
		
		com.deltadental.platform.pcp.stub.ProvidersResponse providersResponse = new com.deltadental.platform.pcp.stub.ProvidersResponse();
		providersResponse.setReturn(stubPcpAssignmentResponse);
		
		ProvidersResponse expectedProvidersResponse = new ProvidersResponse();
		expectedProvidersResponse.setPcpAssignmentResponse(expected);
		
		Mockito.doReturn(providers).when(pcpSearchRequestTransformerMock).transformProvidersRequest(ArgumentMatchers.any(ProvidersRequest.class));
		Mockito.doReturn(providersResponse).when(pcpAssignmentSoapClientMock).providers(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.Providers.class));
		Mockito.doReturn(expectedProvidersResponse).when(pcpSearchResponseTransformerMock).transformProvidersResponse(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.ProvidersResponse.class));
		
		ProvidersResponse actual = pcpSearchServiceImpl.providers(providersRequest);
		assertResults(expectedProvidersResponse, actual);
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformProvidersRequest(ArgumentMatchers.any(ProvidersRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).providers(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.Providers.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformProvidersResponse(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.ProvidersResponse.class));
	}

	private void assertResults(Object expected, Object actual) {
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
	}
}
