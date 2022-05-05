package com.deltadental.pcp.search.controller;

import lombok.extern.slf4j.Slf4j;

//@ExtendWith(SpringExtension.class)
//@EnableAutoConfiguration
//@TestPropertySource("classpath:application-mock.properties")
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//@DisplayName("When Testing PCP Search Service Controller")
@Slf4j
public class PCPSearchServiceControllerTest {
//
//	@Autowired
//	private ObjectMapper mapper;
//	
//	@InjectMocks
//	PCPSearchServiceController  pcpSearchServiceController;
//	
//	@Mock 
//	PCPSearchService pcpSearchServiceMock;
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_GET_PROVIDERS + " success")
//	void testGetProviders() throws Exception {
//		PcpAssignmentRequest pcpAssignmentRequest = mapper
//                .readerFor(PcpAssignmentRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/getProvidersRequest.json"));
//		
//		PCPAssignmentResponse expected = mapper
//                .readerFor(PCPAssignmentResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/getProvidersResponse.json"));
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).getProviders(ArgumentMatchers.any(PcpAssignmentRequest.class));
//		ResponseEntity<PCPAssignmentResponse> responseEntity = pcpSearchServiceController.getProviders(pcpAssignmentRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_FACILITY_SEARCH + " success")
//	void testFacilitySearch() throws Exception {
//		FacilitySearchRequest facilitySearchRequest = mapper
//                .readerFor(FacilitySearchRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/facilitySearchRequest.json"));
//		FacilityResponse expected = mapper
//                .readerFor(FacilityResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/facilitySearchResponse.json"));
//		
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).facilitySearch(ArgumentMatchers.any(FacilitySearchRequest.class));
//		
//		ResponseEntity<FacilityResponse> responseEntity = pcpSearchServiceController.facilitySearch(facilitySearchRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_BUSINESS_LEVELS + " success")
//	void testGetBusinessLevels() throws Exception {
//		BlResolutionResponse expected = mapper
//                .readerFor(BlResolutionResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blResolutionResponse.json"));
//		BlServiceRequest blServiceRequest = mapper
//                .readerFor(BlServiceRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
//		
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).getBusinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
//		ResponseEntity<BlResolutionResponse> responseEntity = pcpSearchServiceController.getBusinessLevels(blServiceRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_BP_AND_BUSINESS_LEVELS + " success")
//	void testGetBPsAndBussinessLevels() throws Exception {
//		
//		BlServiceRequest blServiceRequest = mapper
//                .readerFor(BlServiceRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
//		
//		BPBLResolutionResponse expected = mapper
//                .readerFor(BPBLResolutionResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/bpblResolutionResponse.json"));
//		
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).getBPsAndBussinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
//		ResponseEntity<BPBLResolutionResponse> responseEntity = pcpSearchServiceController.getBPsAndBussinessLevels(blServiceRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_STATE_PCP_ASSIGNMENT + " success")
//	void testGetStatePCPAssignment() throws Exception {
//		StatePcpAssignmentRequest statePcpAssignmentRequest = mapper
//                .readerFor(StatePcpAssignmentRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/statePcpAssignmentRequest.json"));
//		StatePcpAssignmentResponse expected = mapper
//                .readerFor(StatePcpAssignmentResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/statePcpAssignmentResponse.json"));
//		
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).getStatePCPAssignment(ArgumentMatchers.any(StatePcpAssignmentRequest.class));
//		ResponseEntity<StatePcpAssignmentResponse> responseEntity = pcpSearchServiceController.getStatePCPAssignment(statePcpAssignmentRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_GROUP_BENEFIT_PACK_BUSINESS_LEVEL + " success")
//	void testGroupBenefitPackBussinessLevel() throws Exception {
//		BlServiceRequest blServiceRequest = mapper
//                .readerFor(BlServiceRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
//		BPBLResolutionResponse expected = mapper
//                .readerFor(BPBLResolutionResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/bpblResolutionResponse.json"));
//
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).groupBenefitPackBussinessLevel(ArgumentMatchers.any(BlServiceRequest.class));
//		ResponseEntity<BPBLResolutionResponse> responseEntity = pcpSearchServiceController.groupBenefitPackBussinessLevel(blServiceRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_PROVIDER_VALIDATION + " success")
//	void testProviderValidate() throws Exception {
//		PcpAssignmentRequest pcpAssignmentRequest = mapper
//                .readerFor(PcpAssignmentRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providerValidateRequest.json"));
//		PCPAssignmentResponse expected = mapper
//                .readerFor(PCPAssignmentResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providerValidateResponse.json"));
//		
//		Mockito.doReturn(expected).when(pcpSearchServiceMock).providerValidate(ArgumentMatchers.any(PcpAssignmentRequest.class));
//		ResponseEntity<PCPAssignmentResponse> responseEntity = pcpSearchServiceController.providerValidate(pcpAssignmentRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//	
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_PROVIDERS + " success")
//	void testProviders() throws Exception {
//		ProvidersRequest providersRequest = mapper
//                .readerFor(ProvidersRequest.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providersRequest.json"));
//		PCPAssignmentResponse expected = mapper
//                .readerFor(PCPAssignmentResponse.class)
//                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providersResponse.json"));
//		
//		ProvidersResponse providersResponse = new ProvidersResponse();
//		providersResponse.setPcpAssignmentResponse(expected);
//		
//		Mockito.doReturn(providersResponse).when(pcpSearchServiceMock).providers(ArgumentMatchers.any(ProvidersRequest.class));
//		
//		ResponseEntity<ProvidersResponse> responseEntity = pcpSearchServiceController.providers(providersRequest);
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	//@Test
//    @DisplayName("Testing " + PCPSearchServiceConstants.SUMMARY_RETRIEVE_DISTINCT_EXCEPTION_GROUPS + " success")
//	void testRetrieveDistinctExceptionGroups() throws Exception {
//		RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = new RetrieveDistinctExceptionGroupsRes();
//		retrieveDistinctExceptionGroupsRes.setRetrieveDistinctExceptionGroupsResponse("PASS");
//		
//		Mockito.doReturn(retrieveDistinctExceptionGroupsRes).when(pcpSearchServiceMock).retrieveDistinctExceptionGroups(ArgumentMatchers.any(String.class));
//		ResponseEntity<RetrieveDistinctExceptionGroupsRes> responseEntity = pcpSearchServiceController.retrieveDistinctExceptionGroups("test");
//	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//		
//	}

}
