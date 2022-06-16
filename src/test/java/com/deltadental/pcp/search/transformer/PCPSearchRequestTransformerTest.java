package com.deltadental.pcp.search.transformer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deltadental.pcp.search.config.ApplicationConfig;
import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.Providers;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application-mock.properties", "spring.config.enabled=false"})
@TestPropertySource("classpath:application-mock.properties")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Testing PCP Search Request Transformer Test")
public class PCPSearchRequestTransformerTest {
	
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;
	
	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");	
	
   @BeforeEach
   public void init() {
	  pcpSearchRequestTransformer = new PCPSearchRequestTransformer();
   }
	
	@Test
	public void testTransformPcpValidateRequest() {	
		PCPValidateRequest pcpValidateRequestValidate = PCPValidateRequest.builder()
				.contractId("test123")
				.lookAheadDays("60")
				.memberType("01")
				.mtvPersonId("001234")
				.mtvPersonId("personId003")
				.pcpEffDate(LocalDate.parse("02-04-2022",df))
				.pcpEndDate(LocalDate.parse("03-04-2022",df))
				.product("120012001")
				.providerId("001234110")
				.recordIdentifier("DC1234")
				.sourceSystem("test_source")
				.build();
		PcpValidate pcpValidate = pcpSearchRequestTransformer.transformPcpValidateRequest(pcpValidateRequestValidate);
		Assertions.assertNotNull(pcpValidate);  
		Assertions.assertEquals(pcpValidate.getArg0().getContractId(),"test123");	
		Assertions.assertEquals(pcpValidate.getArg0().getLookAheadDays(),"60");
		Assertions.assertEquals(pcpValidate.getArg0().getMemberType(),"01");
		Assertions.assertEquals(pcpValidate.getArg0().getMtvPersonId(),"personId003");
		Assertions.assertEquals(pcpValidate.getArg0().getProduct(),"120012001");
		Assertions.assertEquals(pcpValidate.getArg0().getProviderId(),"001234110");
		Assertions.assertEquals(pcpValidate.getArg0().getRecordIdentifier(),"DC1234");
		Assertions.assertEquals(pcpValidate.getArg0().getSourceSystem(),"test_source");
	}
	
	@Test
	public void testtransformProvidersRequest() {	
		ProvidersRequest providersRequest = ProvidersRequest.builder()
				.address(new Address())
				.autoAssignment("4321")
				.contractId("prod01")
				.memberId("member01")
				.pcpEffectiveDate("02-04-2022")
				.sourceSystem("source_01")
				.build();
		Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
		Assertions.assertNotNull(providers);  
		Assertions.assertEquals(providers.getArg0().getContractID(),"prod01");
		Assertions.assertEquals(providers.getArg0().getAutoAssignment(),"4321");
		Assertions.assertEquals(providers.getArg0().getMemberId(),"member01");
		Assertions.assertEquals(providers.getArg0().getPcpEffectiveDate(),"02-04-2022");
		Assertions.assertEquals(providers.getArg0().getSourceSystem(),"source_01");
	}
	
	

}
