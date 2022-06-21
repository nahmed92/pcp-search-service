package com.deltadental.pcp.search.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;

public class PCPValidateRequestValidatorTest {

	private PCPValidateRequestValidator validator;

	@BeforeEach
	public void init() {
		validator = new PCPValidateRequestValidator();
	}

	@Test
    public void testContractIdDigitOnlyValidate_Failed(){
    	try {
		PCPValidateRequest pcpValidateRequestValidate = PCPValidateRequest.builder()
				.contractId("test123")
				.lookAheadDays("60")
				.memberType("01")
				.mtvPersonId("001234")
				.mtvPersonId("personId003")
				.product("120012001")
				.providerId("001234110")
				.recordIdentifier("DC1234")
				.sourceSystem("Test_system")
				.build();
    	validator.validate(pcpValidateRequestValidate);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.name());
    	}
	}

	@Test
	public void testmemberTypeBlankValidate_Failed() {
		try {
			PCPValidateRequest pcpValidateRequestValidate = PCPValidateRequest.builder().contractId("12345")
					.lookAheadDays("60").mtvPersonId("001234").mtvPersonId("personId003")
					.product("120012001").providerId("001234110").recordIdentifier("DC1234").sourceSystem("Test_system")
					.build();
			validator.validate(pcpValidateRequestValidate);
		} catch (ServiceException exception) {
			Assertions.assertEquals(exception.getErrorCode().toString(),
					PCPSearchServiceErrors.PROVIDERS_MEMBERID.name());
		}
	}
	
	@Test
    public void testSouceSystemShouldNotBalankValidate_Failed(){
    	try {
		PCPValidateRequest pcpValidateRequestValidate = PCPValidateRequest.builder()
				.contractId("12345")
				.lookAheadDays("60")
				.memberType("01")
				.mtvPersonId("001234")
				.mtvPersonId("personId003")
				.product("120012001")
				.providerId("001234110")
				.recordIdentifier("DC1234")
				.build();
    	validator.validate(pcpValidateRequestValidate);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.name());
    	}
	}
	
	@Test
    public void testRequestIsNullValidate_Failed(){
    	try {
    	validator.validate(null);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PCP_VALIDATE_REQUEST.name());
    	}
	}
	
	@Test
    public void testallowDigitsOnly(){
    	boolean allow = validator.allowDigitsOnly("1234edf");
    	Assertions.assertEquals(allow, false);
    	
	}


}
