package com.deltadental.pcp.search.transformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deltadental.pcp.search.config.ApplicationConfig;
import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;
import com.deltadental.platform.pcp.stub.Address;
import com.deltadental.platform.pcp.stub.BenefitPackage;
import com.deltadental.platform.pcp.stub.BusinessLevels;
import com.deltadental.platform.pcp.stub.EnrolleeDetail;
import com.deltadental.platform.pcp.stub.OfficeHour;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.PcpProvider;
import com.deltadental.platform.pcp.stub.PcpResponse;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.ProvidersResponse;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties", "spring.config.enabled=false"})
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Testing PCP Search Response Transformer Test")
public class PCPSearchResponseTransformerTest {

	private  PCPSearchResponseTransformer  pcpSearchResponseTransformer;
	   @BeforeEach
	   public void init() {
		   pcpSearchResponseTransformer = new PCPSearchResponseTransformer();
	   }
	   
	@Test
	public void testTransformPcpValidateResponse() {
		PcpValidateResponse pcpValidateResponse = new PcpValidateResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		PcpResponse pcpResponse = new PcpResponse();
		pcpResponse.getEnrollees().add(null);
		pcpAssignmentResponse.getPcpResponses().add(pcpResponse);
		pcpValidateResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.PCPAssignmentResponse response  = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);		
		
		Assertions.assertNotNull(response);  
		Assertions.assertEquals(response.getProcessStatusCode(),"Code_01");
		Assertions.assertEquals(response.getProcessStatusDescription(),"Assginment Process Status");
		Assertions.assertEquals(response.getSourceSystem(),"Test_source_system");
	}
	
	@Test
	public void testTransformPcpValidateResponseWhenPcpResponseIsNull() {
		PcpValidateResponse pcpValidateResponse = new PcpValidateResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		pcpAssignmentResponse.getPcpResponses().add(null);
		pcpValidateResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.PCPAssignmentResponse response  = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);		
		
		Assertions.assertNotNull(response);  
		Assertions.assertEquals(response.getProcessStatusCode(),"Code_01");
		Assertions.assertEquals(response.getProcessStatusDescription(),"Assginment Process Status");
		Assertions.assertEquals(response.getSourceSystem(),"Test_source_system");
	}
	
	@Test
	public void testTransformPcpValidateResponseIsNull() {
		PcpValidateResponse pcpValidateResponse = new PcpValidateResponse();
		pcpValidateResponse.setReturn(null);	
		com.deltadental.pcp.search.domain.PCPAssignmentResponse response  = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);		
		
		Assertions.assertNull(response);  
	}
	
	@Test
	public void testTransformProvidersResponse() {
		com.deltadental.platform.pcp.stub.ProvidersResponse providersResponse =new com.deltadental.platform.pcp.stub.ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		providersResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);		
		
		Assertions.assertNotNull(response);  
		Assertions.assertEquals(response.getPcpAssignmentResponse().getProcessStatusCode(),"Code_01");
		Assertions.assertEquals(response.getPcpAssignmentResponse().getProcessStatusDescription(),"Assginment Process Status");
		Assertions.assertEquals(response.getPcpAssignmentResponse().getSourceSystem(),"Test_source_system");
	}
	
	@Test
	public void testTransformProvidersResponseWithPCPResponse() {
		ProvidersResponse providersResponse =new ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		PcpResponse pcpResponse = new PcpResponse();
		pcpResponse.setContractID("contrac01");
		EnrolleeDetail enrolleedetail = new EnrolleeDetail();
		
		BenefitPackage benifitPacakge =new BenefitPackage();
		benifitPacakge.setBenefitPackageId("123");
		benifitPacakge.setBpideffectiveDate("02-06-2022");
		benifitPacakge.setBpidendDate("30-06-2022");
		enrolleedetail.getBenefitpackages().add(benifitPacakge);
		
		BusinessLevels businesslevels = new BusinessLevels();
		businesslevels.setBusinessLevel4("BSLevel4");
		businesslevels.setBusinessLevel5("BSLevel5");
		businesslevels.setBusinessLevel6("BSLevel6");
		businesslevels.setBusinessLevel7("BSLevel7");
		enrolleedetail.getBusinesslevels().add(businesslevels);
		
		enrolleedetail.setDivisionNumber("Division1");
		enrolleedetail.setEnrolleeStatusCode("status1");
		enrolleedetail.getErrorMessages().add("Error message1");
		enrolleedetail.setGroupNumber("group1");
		enrolleedetail.setMemberType("01");
		enrolleedetail.setNumberOfFacilities("Fac1");
			
		PcpProvider pcpProvider = new PcpProvider();
		pcpProvider.setSpecialtyCode("01");
		pcpProvider.getBusinesslevels().add(businesslevels);
		pcpProvider.setDistance("23");
		pcpProvider.setGroupPracticeNumber("2345");
		OfficeHour officeHour = new OfficeHour(); 
		officeHour.getOfficeHours().add("7");
		pcpProvider.setOfficeHours(officeHour);

		enrolleedetail.getPcpproviders().add(pcpProvider);
		enrolleedetail.getErrorMessages().add("Fail");
		
		Address address = new Address();
		address.setAddressLine1("Abc Address");
		address.setCity("ABQ");
		address.setZipCode("87111");
		pcpProvider.setProviderAddress(address);
	
		
		pcpResponse.getEnrollees().add(enrolleedetail);
		pcpAssignmentResponse.getPcpResponses().add(pcpResponse);
		providersResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		
		Assertions.assertNotNull(response);  
	}
	
	@Test
	public void testTransformProvidersResponseWithNOPCPResponseError() {
		ProvidersResponse providersResponse =new ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		PcpResponse pcpResponse = new PcpResponse();
		pcpResponse.setContractID("contrac01");
		EnrolleeDetail enrolleedetail = new EnrolleeDetail();
		
		BenefitPackage benifitPacakge =new BenefitPackage();
		benifitPacakge.setBenefitPackageId("123");
		benifitPacakge.setBpideffectiveDate("02-06-2022");
		benifitPacakge.setBpidendDate("30-06-2022");
		enrolleedetail.getBenefitpackages().add(benifitPacakge);
		
		BusinessLevels businesslevels = new BusinessLevels();
		businesslevels.setBusinessLevel4("BSLevel4");
		businesslevels.setBusinessLevel5("BSLevel5");
		businesslevels.setBusinessLevel6("BSLevel6");
		businesslevels.setBusinessLevel7("BSLevel7");
		enrolleedetail.getBusinesslevels().add(businesslevels);
		
		enrolleedetail.setDivisionNumber("Division1");
		enrolleedetail.setEnrolleeStatusCode("status1");
		enrolleedetail.setGroupNumber("group1");
		enrolleedetail.setMemberType("01");
		enrolleedetail.setNumberOfFacilities("Fac1");
			
		PcpProvider pcpProvider = new PcpProvider();
		pcpProvider.setSpecialtyCode("01");
		pcpProvider.getBusinesslevels().add(businesslevels);
		pcpProvider.setDistance("23");
		pcpProvider.setGroupPracticeNumber("2345");
		OfficeHour officeHour = new OfficeHour(); 
		officeHour.getOfficeHours().add("7");
		pcpProvider.setOfficeHours(officeHour);

		enrolleedetail.getPcpproviders().add(pcpProvider);
		
		Address address = new Address();
		address.setAddressLine1("Abc Address");
		address.setCity("ABQ");
		address.setZipCode("87111");
		pcpProvider.setProviderAddress(address);
	
		
		pcpResponse.getEnrollees().add(enrolleedetail);
		pcpAssignmentResponse.getPcpResponses().add(pcpResponse);
		providersResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		
		Assertions.assertNotNull(response);  
	}
	
	@Test
	public void testTransformProvidersResponseWhenAddressNull() {
		ProvidersResponse providersResponse =new ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		PcpResponse pcpResponse = new PcpResponse();
		pcpResponse.setContractID("contrac01");
		EnrolleeDetail enrolleedetail = new EnrolleeDetail();
		
		enrolleedetail.getBenefitpackages().add(null);
		
		BusinessLevels businesslevels = new BusinessLevels();
		businesslevels.setBusinessLevel4("BSLevel4");
		businesslevels.setBusinessLevel5("BSLevel5");
		businesslevels.setBusinessLevel6("BSLevel6");
		businesslevels.setBusinessLevel7("BSLevel7");
		enrolleedetail.getBusinesslevels().add(businesslevels);
		
		enrolleedetail.setDivisionNumber("Division1");
		enrolleedetail.setEnrolleeStatusCode("status1");
		enrolleedetail.getErrorMessages().add("Error message1");
		enrolleedetail.setGroupNumber("group1");
		enrolleedetail.setMemberType("01");
		enrolleedetail.setNumberOfFacilities("Fac1");
			
		PcpProvider pcpProvider = new PcpProvider();
		pcpProvider.setSpecialtyCode("01");
		pcpProvider.getBusinesslevels().add(businesslevels);
		pcpProvider.setDistance("23");
		pcpProvider.setGroupPracticeNumber("2345");
		pcpProvider.setOfficeHours(null);

		enrolleedetail.getPcpproviders().add(pcpProvider);
		enrolleedetail.getErrorMessages().add("Fail");
		
		pcpProvider.setProviderAddress(null);
	
		
		pcpResponse.getEnrollees().add(enrolleedetail);
		pcpAssignmentResponse.getPcpResponses().add(pcpResponse);
		providersResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		
		Assertions.assertNotNull(response);  
	}
	
	@Test
	public void testTransformProvidersResponseWhenPcpAssignmentResponseNull() {
		ProvidersResponse providersResponse =new ProvidersResponse();
		providersResponse.setReturn(null);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		
		Assertions.assertNotNull(response);  
	}

	
	@Test
	public void testTransformProvidersResponseWithPCPProviderIsnull() {
		ProvidersResponse providersResponse =new ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		PcpResponse pcpResponse = new PcpResponse();
		pcpResponse.setContractID("contrac01");
		EnrolleeDetail enrolleedetail = new EnrolleeDetail();

		BusinessLevels businesslevels = new BusinessLevels();
		businesslevels.setBusinessLevel4("BSLevel4");
		businesslevels.setBusinessLevel5("BSLevel5");
		businesslevels.setBusinessLevel6("BSLevel6");
		businesslevels.setBusinessLevel7("BSLevel7");
		enrolleedetail.getBusinesslevels().add(null);
		
		enrolleedetail.setDivisionNumber("Division1");
		enrolleedetail.setEnrolleeStatusCode("status1");
		enrolleedetail.getErrorMessages().add("Error message1");
		enrolleedetail.setGroupNumber("group1");
		enrolleedetail.setMemberType("01");
		enrolleedetail.setNumberOfFacilities("Fac1");
			
		PcpProvider pcpProvider = new PcpProvider();
		pcpProvider.setSpecialtyCode("01");
		pcpProvider.getBusinesslevels().add(businesslevels);
		pcpProvider.setDistance("23");
		pcpProvider.setGroupPracticeNumber("2345");
		OfficeHour officeHour = new OfficeHour(); 
		officeHour.getOfficeHours().add("7");
		pcpProvider.setOfficeHours(officeHour);

		enrolleedetail.getPcpproviders().add(null);
		enrolleedetail.getErrorMessages().add("Fail");
		
		Address address = new Address();
		address.setAddressLine1("Abc Address");
		address.setCity("ABQ");
		address.setZipCode("87111");
		pcpProvider.setProviderAddress(address);
	
		
		pcpResponse.getEnrollees().add(enrolleedetail);
		pcpAssignmentResponse.getPcpResponses().add(pcpResponse);
		providersResponse.setReturn(pcpAssignmentResponse);	
		com.deltadental.pcp.search.domain.ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		
		Assertions.assertNotNull(response);  

	}
	
	@Test
	public void testMapFacility() {
		FacilitySearchEntity facilityEntity = FacilitySearchEntity.builder()
				.address("Test Address")
				.contracted("1234")
				.effectiveDate("02-02-2022")
				.facilityId("FAC_123")
				.facilityStatus("Status_test")
				.enrollStatus("1234")
				.providerLanguages("English")
				.build();
       Facility facility =   pcpSearchResponseTransformer.mapFacility(facilityEntity);
		Assertions.assertNotNull(facility);  
		Assertions.assertEquals(facility.getAddress(),"Test Address");
		Assertions.assertEquals(facility.getContracted(),"1234");
		Assertions.assertEquals(facility.getEffectiveDate(),"02-02-2022");		
		Assertions.assertEquals(facility.getFacilityId(),"FAC_123");
		Assertions.assertEquals(facility.getFacilityStatus(),"Status_test");
		Assertions.assertEquals(facility.getEnrollStatus(),"1234");
	}
	
	@Test
	public void testMapFacilityWhenEffectiveDateIsNull() {
		FacilitySearchEntity facilityEntity = FacilitySearchEntity.builder()
				.address("Test Address")
				.contracted("1234")
				.effectiveDate("NA")
				.facilityId("FAC_123")
				.facilityStatus("Status_test")
				.enrollStatus("1234")
				.providerLanguages("English")
				.build();
       Facility facility =   pcpSearchResponseTransformer.mapFacility(facilityEntity);
		Assertions.assertNotNull(facility);  
		Assertions.assertEquals(facility.getAddress(),"Test Address");
		Assertions.assertEquals(facility.getContracted(),"1234");
		Assertions.assertEquals(facility.getEffectiveDate(),"");		
		Assertions.assertEquals(facility.getFacilityId(),"FAC_123");
		Assertions.assertEquals(facility.getFacilityStatus(),"Status_test");
		Assertions.assertEquals(facility.getEnrollStatus(),"1234");
	}
	
	@Test
	public void testMapFacilityIfFacilityEntityIsNull() {
       Facility facility =   pcpSearchResponseTransformer.mapFacility(null);
		Assertions.assertNull(facility);  
	}

}
