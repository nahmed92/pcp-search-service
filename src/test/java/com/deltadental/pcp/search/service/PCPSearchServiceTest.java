package com.deltadental.pcp.search.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;
import com.deltadental.pcp.search.pd.repos.FacilitySearchRepo;
import com.deltadental.pcp.search.service.impl.ProvidersAuditService;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.common.exception.ServiceException;
import com.deltadental.platform.pcp.stub.PcpSearchRequest;
import com.deltadental.platform.pcp.stub.Providers;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = { ApplicationConfig.class })
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties",
		"spring.config.enabled=false" })
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When PCP Search Service")
public class PCPSearchServiceTest {

	private PCPSearchService pcpSearchService;

	@Autowired
	private ObjectMapper mapper;

	@Mock
	private PCPAssignmentSoapClient pcpAssignmentSoapClientMock;

	@Mock
	private PCPSearchRequestTransformer pcpSearchRequestTransformerMock;

	@Mock
	private ProvidersAuditService providersAuditServiceMock;

	@Mock
	private PCPSearchResponseTransformer pcpSearchResponseTransformerMock;

	@Mock
	private FacilitySearchRepo facilitySearchRepoMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		pcpSearchService = new PCPSearchService();
		pcpSearchService.setPcpAssignmentSoapClient(pcpAssignmentSoapClientMock);
		pcpSearchService.setPcpSearchRequestTransformer(pcpSearchRequestTransformerMock);
		pcpSearchService.setPcpSearchResponseTransformer(pcpSearchResponseTransformerMock);
		pcpSearchService.setProvidersAuditService(providersAuditServiceMock);
		pcpSearchService.setFacilitySearchRepo(facilitySearchRepoMock);
	}

	@Test // FIXME:
	@DisplayName("Testing Search Providers success")
	void testSearchProviders() throws Exception {
		ProvidersRequest providersRequest = ProvidersRequest.builder().address(new Address()).autoAssignment("4321")
				.contractId("prod01").memberId("member01").pcpEffectiveDate("02-04-2022").sourceSystem("source_01")
				.build();
		Providers providers = new Providers();
		PcpSearchRequest request = new PcpSearchRequest();
		request.setContractID("123");
		request.setMemberId("01");
		providers.setArg0(request);
		Mockito.doReturn(providers).when(pcpSearchRequestTransformerMock)
				.transformProvidersRequest(ArgumentMatchers.any(ProvidersRequest.class));
		pcpSearchService.searchProviders(providersRequest);
	}

	@Test // FIXME:
	@DisplayName("Testing Validate success")
	void testValidate() throws Exception {
		PCPValidateRequest pcpValidateRequest = PCPValidateRequest.builder().contractId("123").memberType("001")
				.mtvPersonId("mtv001").build();
		Providers providers = new Providers();
		PcpSearchRequest request = new PcpSearchRequest();
		request.setContractID("123");
		request.setMemberId("01");
		providers.setArg0(request);
		Mockito.doReturn(providers).when(pcpSearchRequestTransformerMock)
				.transformProvidersRequest(ArgumentMatchers.any(ProvidersRequest.class));
		pcpSearchService.validate(pcpValidateRequest);
	}

	@Test // FIXME:
	@DisplayName("Testing Facility is Blank Exception")
	void testsearchFacilityIsBlank() throws Exception {
		try {
			pcpSearchService.searchFacility("");
		} catch (ServiceException exception) {
			Assertions.assertEquals(exception.getErrorCode().toString(),
					PCPSearchServiceErrors.FACILITY_ID_REQUIRED.name());
		}
	}

	@Test // FIXME:
	@DisplayName("Testing Facility is null Exception")
	void testSearchFacilityIsNull() throws Exception {
		FacilitySearchEntity facility = FacilitySearchEntity.builder().contracted("1233").facilityId("123")
				.facilityStatus("Active").build();
		Mockito.doReturn(facility).when(facilitySearchRepoMock).searchFacilityByFacilityId("123");
		try {
			pcpSearchService.searchFacility("123");
		} catch (ServiceException exception) {
			Assertions.assertEquals(exception.getErrorCode().toString(),
					PCPSearchServiceErrors.FACILITY_NOT_FOUND.name());
		}
	}
}
