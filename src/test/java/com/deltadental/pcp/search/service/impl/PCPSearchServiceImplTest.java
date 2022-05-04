package com.deltadental.pcp.search.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import com.deltadental.pcp.search.domain.BPBLResolutionResponse;
import com.deltadental.pcp.search.domain.BlResolutionResponse;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.EnrolleeBLRespose;
import com.deltadental.pcp.search.domain.FacilityResponse;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.domain.RetrieveDistinctExceptionGroupsRes;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.pcp.stub.BenefitPackage;
import com.deltadental.platform.pcp.stub.BpBusinessLevels;
import com.deltadental.platform.pcp.stub.BpblResolutionResponse;
import com.deltadental.platform.pcp.stub.BusinessLevels;
import com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse;
import com.deltadental.platform.pcp.stub.Facility;
import com.deltadental.platform.pcp.stub.FacilitySearch;
import com.deltadental.platform.pcp.stub.FacilitySearchResponse;
import com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetBussinessLevels;
import com.deltadental.platform.pcp.stub.GetBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignment;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignmentResponse;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevelResponse;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroups;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroupsResponse;
import com.deltadental.platform.pcp.stub.StatePcpAssignmentResponse;
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
	
	@Test
	@DisplayName("Testing Facility Search success")
	void testFacilitySearch() throws Exception  {
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		
		FacilitySearchRequest facilitySearchRequest = mapper
                .readerFor(FacilitySearchRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/facilitySearchRequest.json"));
		FacilityResponse expected = mapper
                .readerFor(FacilityResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/facilitySearchResponse.json"));
		
		FacilitySearch stubFacilitySearch = pcpSearchRequestTransformer.transformFacilitySearch(facilitySearchRequest);
		com.deltadental.platform.pcp.stub.FacilityResponse facilityResponse = transformDomainFacilityResponseToStubFacilityResponse(expected);
		FacilitySearchResponse searchResponse = new FacilitySearchResponse();
		searchResponse.setReturn(facilityResponse);
		
		Mockito.doReturn(stubFacilitySearch).when(pcpSearchRequestTransformerMock).transformFacilitySearch(ArgumentMatchers.any(FacilitySearchRequest.class));
		Mockito.doReturn(searchResponse).when(pcpAssignmentSoapClientMock).facilitySearch(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.FacilitySearch.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformFaclilitySearchResponse(ArgumentMatchers.any(FacilitySearchResponse.class));
		
		FacilityResponse actual = pcpSearchServiceImpl.facilitySearch(facilitySearchRequest);
		
		assertResults(expected, actual);
		
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformFacilitySearch(ArgumentMatchers.any(FacilitySearchRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).facilitySearch(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.FacilitySearch.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformFaclilitySearchResponse(ArgumentMatchers.any(FacilitySearchResponse.class));
	}

	@Test
	@DisplayName("Testing Get Benefit Packages and Business Levels success")
	void testGetBPsAndBussinessLevels()  throws Exception {
		BlServiceRequest blServiceRequest = mapper
                .readerFor(BlServiceRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
		BPBLResolutionResponse expected = mapper
                .readerFor(BPBLResolutionResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/bpblResolutionResponse.json"));
		
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
		
		GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = new GetBPsAndBussinessLevelsResponse();
		BpblResolutionResponse _return = transformBpblResolutionResponse(expected);
		getBPsAndBussinessLevelsResponse.setReturn(_return);		
		
		Mockito.doReturn(getBussinessLevels).when(pcpSearchRequestTransformerMock).transformGetBussinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.doReturn(getBPsAndBussinessLevelsResponse).when(pcpAssignmentSoapClientMock).getBPsAndBussinessLevels(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevels.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformGetBPsAndBussinessLevelsResponse(ArgumentMatchers.any(GetBPsAndBussinessLevelsResponse.class));
		
		BPBLResolutionResponse actual = pcpSearchServiceImpl.getBPsAndBussinessLevels(blServiceRequest);
		
		assertResults(expected, actual);
		
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformGetBussinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).getBPsAndBussinessLevels(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevels.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformGetBPsAndBussinessLevelsResponse(ArgumentMatchers.any(GetBPsAndBussinessLevelsResponse.class));
	}

	@Test
	@DisplayName("Testing Get Business Levels success")
	void testGetBusinessLevels() throws Exception  {
		BlServiceRequest blServiceRequest = mapper
                .readerFor(BlServiceRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
		
		BlResolutionResponse expected = mapper
                .readerFor(BlResolutionResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blResolutionResponse.json"));
		
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
		
		GetBussinessLevelsResponse getBussinessLevelsResponse = extractGetBLResponse(expected);
		
		Mockito.doReturn(getBussinessLevels).when(pcpSearchRequestTransformerMock).transformGetBussinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.doReturn(getBussinessLevelsResponse).when(pcpAssignmentSoapClientMock).getBussinessLevels(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetBussinessLevels.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformStubGetBussinessLevelsResponse(ArgumentMatchers.any(GetBussinessLevelsResponse.class));
		
		BlResolutionResponse actual = pcpSearchServiceImpl.getBusinessLevels(blServiceRequest);
		
		assertResults(expected, actual);
		
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformGetBussinessLevels(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).getBussinessLevels(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetBussinessLevels.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformStubGetBussinessLevelsResponse(ArgumentMatchers.any(GetBussinessLevelsResponse.class));
	}

	@Test
	@DisplayName("Testing Get ProvidersRequest success")
	void testGetProviders() throws Exception {
		PcpAssignmentRequest pcpAssignmentRequest = mapper
                .readerFor(PcpAssignmentRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/getProvidersRequest.json"));
		PCPAssignmentResponse expected = mapper
                .readerFor(PCPAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/getProvidersResponse.json"));
		
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = new com.deltadental.platform.pcp.stub.PcpAssignmentRequest(); 
		objectDeepCopy(pcpAssignmentRequest, assignmentRequest);
		GetProviders getProviders = new GetProviders();
		getProviders.setArg0(assignmentRequest);
		
		GetProvidersResponse getProvidersResponse = new GetProvidersResponse();
		PcpAssignmentResponse _return = new  PcpAssignmentResponse();
		objectDeepCopy(expected, _return);
		getProvidersResponse.setReturn(_return);
		
		Mockito.doReturn(getProviders).when(pcpSearchRequestTransformerMock).transformGetProviders(ArgumentMatchers.any(PcpAssignmentRequest.class));
		Mockito.doReturn(getProvidersResponse).when(pcpAssignmentSoapClientMock).getProviders(ArgumentMatchers.any(GetProviders.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformGetProvidersResponse(ArgumentMatchers.any(GetProvidersResponse.class));
		
		PCPAssignmentResponse actual = pcpSearchServiceImpl.getProviders(pcpAssignmentRequest);
		
		assertResults(expected, actual);
		
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformGetProviders(ArgumentMatchers.any(PcpAssignmentRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).getProviders(ArgumentMatchers.any(GetProviders.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformGetProvidersResponse(ArgumentMatchers.any(GetProvidersResponse.class));
	}

	@Test
	@DisplayName("Testing State PCP Assignment success")
	void testGetStatePCPAssignment() throws Exception {
		
		StatePcpAssignmentRequest statePcpAssignmentRequest = mapper
                .readerFor(StatePcpAssignmentRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/statePcpAssignmentRequest.json"));
		com.deltadental.pcp.search.domain.StatePcpAssignmentResponse expected = mapper
                .readerFor(com.deltadental.pcp.search.domain.StatePcpAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/statePcpAssignmentResponse.json"));
		
		com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest stubStatePcpAssignmentRequest = new com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest();
		objectDeepCopy(statePcpAssignmentRequest, stubStatePcpAssignmentRequest);
		GetStatePCPAssignment getStatePCPAssignment = new GetStatePCPAssignment();
		getStatePCPAssignment.setArg0(stubStatePcpAssignmentRequest);
		
		GetStatePCPAssignmentResponse statePCPAssignmentResponse = new GetStatePCPAssignmentResponse(); 
		StatePcpAssignmentResponse  pcpAssignmentResponse = new StatePcpAssignmentResponse();
		objectDeepCopy(expected, pcpAssignmentResponse);
		statePCPAssignmentResponse.setReturn(pcpAssignmentResponse);
			
		Mockito.doReturn(getStatePCPAssignment).when(pcpSearchRequestTransformerMock).transformGetStatePCPAssignment(ArgumentMatchers.any(StatePcpAssignmentRequest.class));
		Mockito.doReturn(statePCPAssignmentResponse).when(pcpAssignmentSoapClientMock).getStatePCPAssignment(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetStatePCPAssignment.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformGetStatePCPAssignmentResponse(ArgumentMatchers.any(GetStatePCPAssignmentResponse.class));
		
		com.deltadental.pcp.search.domain.StatePcpAssignmentResponse actual = pcpSearchServiceImpl.getStatePCPAssignment(statePcpAssignmentRequest);
		assertResults(expected, actual);
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformGetStatePCPAssignment(ArgumentMatchers.any(StatePcpAssignmentRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).getStatePCPAssignment(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GetStatePCPAssignment.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformGetStatePCPAssignmentResponse(ArgumentMatchers.any(GetStatePCPAssignmentResponse.class));
	}

	@Test
	@DisplayName("Testing Group Benefit Package Business Level success")
	void testGroupBenefitPackBussinessLevel() throws Exception  {
		BlServiceRequest blServiceRequest = mapper
                .readerFor(BlServiceRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/blServiceRequest.json"));
		BPBLResolutionResponse expected = mapper
                .readerFor(BPBLResolutionResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/bpblResolutionResponse.json"));
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = pcpSearchRequestTransformer.transformGroupBenefitPackBussinessLevel(blServiceRequest);
			
		GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = new GroupBenefitPackBussinessLevelResponse();
		BpblResolutionResponse bpblResolutionResponse = transformBpblResolutionResponse(expected);
		groupBenefitPackBussinessLevelResponse.setReturn(bpblResolutionResponse);
		
		Mockito.doReturn(groupBenefitPackBussinessLevel).when(pcpSearchRequestTransformerMock).transformGroupBenefitPackBussinessLevel(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.doReturn(groupBenefitPackBussinessLevelResponse).when(pcpAssignmentSoapClientMock).groupBenefitPackBussinessLevel(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformStubGroupBenefitPackBussinessLevelResponse(ArgumentMatchers.any(GroupBenefitPackBussinessLevelResponse.class));
		
		BPBLResolutionResponse actual = pcpSearchServiceImpl.groupBenefitPackBussinessLevel(blServiceRequest);
		assertResults(expected, actual);
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformGroupBenefitPackBussinessLevel(ArgumentMatchers.any(BlServiceRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).groupBenefitPackBussinessLevel(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformStubGroupBenefitPackBussinessLevelResponse(ArgumentMatchers.any(GroupBenefitPackBussinessLevelResponse.class));
	}

	@Test
	@DisplayName("Testing Provider Validate success")
	void testProviderValidate() throws Exception  {
		PcpAssignmentRequest providersRequest = mapper
                .readerFor(PcpAssignmentRequest.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providerValidateRequest.json"));
		PCPAssignmentResponse expected = mapper
                .readerFor(PCPAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providerValidateResponse.json"));
		
		ProvidersResponse expectedProvidersResponse = new ProvidersResponse();
		expectedProvidersResponse.setPcpAssignmentResponse(expected);
		
		PCPSearchRequestTransformer pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
		ProviderValidate providerValidate = pcpSearchRequestTransformer.transformProviderValidate(providersRequest);
		
		PcpAssignmentResponse stubPcpAssignmentResponse = mapper
                .readerFor(PcpAssignmentResponse.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("testdata/providerValidateResponse.json"));
		
		com.deltadental.platform.pcp.stub.ProviderValidateResponse providerValidateResponse = new com.deltadental.platform.pcp.stub.ProviderValidateResponse();
		providerValidateResponse.setReturn(stubPcpAssignmentResponse);

		Mockito.doReturn(providerValidate).when(pcpSearchRequestTransformerMock).transformProviderValidate(ArgumentMatchers.any(PcpAssignmentRequest.class));
		Mockito.doReturn(providerValidateResponse).when(pcpAssignmentSoapClientMock).providerValidate(ArgumentMatchers.any(ProviderValidate.class));
		Mockito.doReturn(expected).when(pcpSearchResponseTransformerMock).transformStubProviderValidateResponse(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.ProviderValidateResponse.class));
		
		PCPAssignmentResponse actual = pcpSearchServiceImpl.providerValidate(providersRequest);
		
		assertResults(expected, actual);
		
		Mockito.verify(pcpSearchRequestTransformerMock, times(1)).transformProviderValidate(ArgumentMatchers.any(PcpAssignmentRequest.class));
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).providerValidate(ArgumentMatchers.any(ProviderValidate.class));
		Mockito.verify(pcpSearchResponseTransformerMock, times(1)).transformStubProviderValidateResponse(ArgumentMatchers.any(com.deltadental.platform.pcp.stub.ProviderValidateResponse.class));
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

	@Test
	@DisplayName("Testing Retrieve Distinct Exception Groups success")
	void testRetrieveDistinctExceptionGroups() {
		RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = new RetrieveDistinctExceptionGroupsResponse();
		retrieveDistinctExceptionGroupsResponse.setReturn("PASS");
		Mockito.doReturn(retrieveDistinctExceptionGroupsResponse).when(pcpAssignmentSoapClientMock).retrieveDistinctExceptionGroups(ArgumentMatchers.any(RetrieveDistinctExceptionGroups.class));
		RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = pcpSearchServiceImpl.retrieveDistinctExceptionGroups("test");
		Mockito.verify(pcpAssignmentSoapClientMock, times(1)).retrieveDistinctExceptionGroups(any());
		Assertions.assertNotNull(retrieveDistinctExceptionGroupsRes);
		Assertions.assertEquals("PASS", retrieveDistinctExceptionGroupsRes.getRetrieveDistinctExceptionGroupsResponse());
	}
	
	private void objectDeepCopy(Object source, Object destination) {
		try {
			BeanUtils.copyProperties(destination, source);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GetBussinessLevelsResponse extractGetBLResponse(BlResolutionResponse expected) {
		GetBussinessLevelsResponse getBussinessLevelsResponse = new GetBussinessLevelsResponse(); 
		com.deltadental.platform.pcp.stub.BlResolutionResponse blResolutionResponse = new com.deltadental.platform.pcp.stub.BlResolutionResponse();
		List<com.deltadental.platform.pcp.stub.EnrolleeBLRespose> stubEnrolleeBLResposes = blResolutionResponse.getEnrollee();
		com.deltadental.platform.pcp.stub.EnrolleeBLRespose enrolleeBLRespose = new com.deltadental.platform.pcp.stub.EnrolleeBLRespose();
		List<BenefitPackage> benefitPackages = enrolleeBLRespose.getBenPkgList();
		List<BusinessLevels> businessLevels = enrolleeBLRespose.getBusinessLevels();
		
		List<EnrolleeBLRespose> enrolleeBLResposes = expected.getEnrollee();
		enrolleeBLResposes.forEach(enrollee -> {
			enrollee.getBenefitPackages().forEach(bp -> {
				BenefitPackage  benefitPackage = new BenefitPackage();
				benefitPackage.setBenefitPackageId(bp.getBenefitPackageId());
				benefitPackage.setBpideffectiveDate(bp.getBpIdEffectiveDate());
				benefitPackage.setBpidendDate(bp.getBpIdEndDate());
				benefitPackages.add(benefitPackage);
			});
			enrollee.getBusinessLevels().forEach(bl -> {
				BusinessLevels businessLevel = new BusinessLevels();
				businessLevel.setBusinessLevel4(bl.getBusinessLevel4());
				businessLevel.setBusinessLevel5(bl.getBusinessLevel5());
				businessLevel.setBusinessLevel6(bl.getBusinessLevel6());
				businessLevel.setBusinessLevel7(bl.getBusinessLevel7());
				businessLevel.setClassCode(bl.getClassCode());
				businessLevel.setEffectiveDate(bl.getEffectiveDate());
				businessLevel.setEndDate(bl.getEndDate());
				businessLevel.setNetworkId(bl.getNetworkId());
				businessLevels.add(businessLevel);
			});
			enrolleeBLRespose.setBusinessLevelCount(enrollee.getBusinessLevelCount());
			enrolleeBLRespose.setDivisionNumber(enrollee.getDivisionNumber());
			enrolleeBLRespose.setErrorMessage(enrollee.getErrorMessage());
			enrolleeBLRespose.setGroupNumber(enrollee.getGroupNumber());
			enrolleeBLRespose.setMemberType(enrollee.getMemberType());
			enrolleeBLRespose.setProviderId(enrollee.getProviderId());
			enrolleeBLRespose.setStatusCode(enrollee.getStatusCode());
		});
		stubEnrolleeBLResposes.add(enrolleeBLRespose);
		getBussinessLevelsResponse.setReturn(blResolutionResponse);
		return getBussinessLevelsResponse;
	}

	private com.deltadental.platform.pcp.stub.FacilityResponse transformDomainFacilityResponseToStubFacilityResponse(FacilityResponse expected) {
		com.deltadental.platform.pcp.stub.FacilityResponse facilityResponse = new com.deltadental.platform.pcp.stub.FacilityResponse();
		List<Facility> facilityList = facilityResponse.getFacility();
		expected.getFacility().forEach(domainFacility -> {
			Facility stubFacility = new Facility();
			stubFacility.setAddressLine1(domainFacility.getAddressLine1());
			stubFacility.setAddressLine2(domainFacility.getAddressLine2());
			stubFacility.setAddressLine3(domainFacility.getAddressLine3());
			stubFacility.setCity(domainFacility.getCity());
			stubFacility.setContracted(domainFacility.getContracted());
			stubFacility.setEffectiveDate(domainFacility.getEffectiveDate());
			stubFacility.setEnrollStatus(domainFacility.getEnrollStatus());
			stubFacility.setFacilityID(domainFacility.getFacilityID());
			stubFacility.setFacilityName(domainFacility.getFacilityName());
			stubFacility.setFacilitystatus(domainFacility.getFacilityStatus());
			stubFacility.setPhoneNumber(domainFacility.getPhoneNumber());
			stubFacility.setSpecility(domainFacility.getSpecility());
			stubFacility.setState(domainFacility.getState());
			stubFacility.setZip(domainFacility.getZip());
			facilityList.add(stubFacility);
		});
		return facilityResponse;
	}
	
	private BpblResolutionResponse transformBpblResolutionResponse(BPBLResolutionResponse expected) {
		BpblResolutionResponse bpblResolutionResponse = new BpblResolutionResponse();
		List<EnrolleeBPBLResponse> bpblResponses = bpblResolutionResponse.getEnrollee();
		expected.getEnrollee().forEach(enrollee -> {
			EnrolleeBPBLResponse enrolleeBPBLResponse = new EnrolleeBPBLResponse();
			enrolleeBPBLResponse.setBusinessLevelCount(enrollee.getBusinessLevelCount());
			enrolleeBPBLResponse.setDivisionNumber(enrollee.getDivisionNumber());
			enrolleeBPBLResponse.setErrorMessage(enrollee.getErrorMessage());
			enrolleeBPBLResponse.setGroupNumber(enrollee.getGroupNumber());
			enrolleeBPBLResponse.setMemberType(enrollee.getMemberType());
			enrolleeBPBLResponse.setProviderId(enrollee.getProviderId());
			enrolleeBPBLResponse.setRecordIdentifier(enrollee.getRecordIdentifier());
			enrolleeBPBLResponse.setStatusCode(enrollee.getStatusCode());
			List<BpBusinessLevels> bpBusinessLevels = enrolleeBPBLResponse.getBusinessLevels();
			enrollee.getBusinessLevels().forEach(businessLevels -> {
				BpBusinessLevels bpBusinessLevel = new  BpBusinessLevels();
				BenefitPackage bp = new BenefitPackage();
				bp.setBenefitPackageId(businessLevels.getBenefitPackage().getBenefitPackageId());
				bp.setBpideffectiveDate(businessLevels.getBenefitPackage().getBpIdEffectiveDate());
				bp.setBpidendDate(businessLevels.getBenefitPackage().getBpIdEndDate());
				bpBusinessLevel.setBenPkg(bp);
				bpBusinessLevel.setBusinessLevel4(businessLevels.getBusinessLevel4());
				bpBusinessLevel.setBusinessLevel5(businessLevels.getBusinessLevel5());
				bpBusinessLevel.setBusinessLevel6(businessLevels.getBusinessLevel6());
				bpBusinessLevel.setBusinessLevel7(businessLevels.getBusinessLevel7());
				bpBusinessLevel.setEffectiveDate(businessLevels.getEffectiveDate());
				bpBusinessLevel.setEndDate(businessLevels.getEndDate());
				bpBusinessLevel.setNetworkId(businessLevels.getNetworkId());
				bpBusinessLevels.add(bpBusinessLevel);
			});
			bpblResponses.add(enrolleeBPBLResponse);
		});
		return bpblResolutionResponse;
	}
	
	private void assertResults(Object expected, Object actual) {
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
	}
}
