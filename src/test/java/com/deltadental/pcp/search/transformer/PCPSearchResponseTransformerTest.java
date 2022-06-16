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
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;

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
		pcpValidateResponse.setReturn(pcpAssignmentResponse);	
		PCPAssignmentResponse response  = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);		
		
		Assertions.assertNotNull(response);  
		Assertions.assertEquals(response.getProcessStatusCode(),"Code_01");
		Assertions.assertEquals(response.getProcessStatusDescription(),"Assginment Process Status");
		Assertions.assertEquals(response.getSourceSystem(),"Test_source_system");
	}
	
	@Test
	public void testTransformProvidersResponse() {
		com.deltadental.platform.pcp.stub.ProvidersResponse providersResponse =new com.deltadental.platform.pcp.stub.ProvidersResponse();
		PcpAssignmentResponse pcpAssignmentResponse = new PcpAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode("Code_01");
		pcpAssignmentResponse.setProcessStatusDescription("Assginment Process Status");
		pcpAssignmentResponse.setSourceSystem("Test_source_system");
		providersResponse.setReturn(pcpAssignmentResponse);	
		ProvidersResponse response  = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);		
		
		Assertions.assertNotNull(response);  
		Assertions.assertEquals(response.getPcpAssignmentResponse().getProcessStatusCode(),"Code_01");
		Assertions.assertEquals(response.getPcpAssignmentResponse().getProcessStatusDescription(),"Assginment Process Status");
		Assertions.assertEquals(response.getPcpAssignmentResponse().getSourceSystem(),"Test_source_system");
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
	
	

}
