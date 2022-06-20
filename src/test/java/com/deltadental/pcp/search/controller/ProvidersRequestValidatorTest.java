package com.deltadental.pcp.search.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;

public class ProvidersRequestValidatorTest {

	private ProvidersRequestValidator validator;

	@BeforeEach
	public void init() {
		validator = new ProvidersRequestValidator();
	}

	@Test
    public void testProvidersRequestAutoAssignBlankValidate_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.contractId("prod01")
    				.memberId("member01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PCP_SEARCH_001.name());
    	}
	}
	
	@Test
    public void testProvidersRequestAutoAssignInvalidFlag_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Z")
    				.contractId("prod01")
    				.memberId("member01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PCP_SEARCH_002.name());
    	}
	}
	
	@Test
    public void testProvidersRequestContractIdNull_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.memberId("member01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_CONTRACTID.name());
    	}
	}
	
	@Test
    public void testProvidersRequestContractIDDigitOnly_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("prod01")
    				.memberId("member01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSMEMBERIDNotNull_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_MEMBERID.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSMEMBERIDLengthGreaterThen2_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("12345")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_MEMBERID.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSEffectiveDateNotBlnk_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("01")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSEffectiveDatePatteren_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("01")
    				.pcpEffectiveDate("2022/12/01")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSUserId_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("01")
    				.pcpEffectiveDate("02-04-2022")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSOurceSystemIsEmpty_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(new Address())
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.PROVIDERS_USERID.name());
    	}
	}
	
	@Test
    public void testProvidersRequestPROVIDERSAdressFieldIsnull_Failed(){
    	try {
    		ProvidersRequest providersRequest = ProvidersRequest.builder()
    				.address(null)
    				.autoAssignment("Y")
    				.contractId("12344")
    				.memberId("01")
    				.pcpEffectiveDate("02-04-2022")
    				.sourceSystem("source_01")
    				.userId("1234")
    				.build();
    	validator.validateProvidersRequest(providersRequest);
    	}catch(ServiceException exception) {
    		Assertions.assertEquals(exception.getErrorCode().toString() ,
    				PCPSearchServiceErrors.ADDRESS_REQUIRED.name());
    	}
	}
    	
    	@Test
        public void testProvidersRequestPROVIDERSAdressZipCodeFieldIsnull_Failed(){
        	try {
        		Address address = Address.builder()
        				.addressLine1("10700 Acadmy")
        				.city("Albu")
        				.build();
        	
        		ProvidersRequest providersRequest = ProvidersRequest.builder()
        				.address(address)
        				.autoAssignment("Y")
        				.contractId("12344")
        				.memberId("01")
        				.pcpEffectiveDate("02-04-2022")
        				.sourceSystem("source_01")
        				.userId("1234")
        				.build();
        	validator.validateProvidersRequest(providersRequest);
        	}catch(ServiceException exception) {
        		Assertions.assertEquals(exception.getErrorCode().toString() ,
        				PCPSearchServiceErrors.PROVIDERS_ZIP.name());
        	}
	}
    	
    	@Test
        public void testProvidersRequestPROVIDERSAdressZipCodeFormatNotCorrect_Failed(){
        	try {
        		Address address = Address.builder()
        				.addressLine1("10700 Acadmy")
        				.city("Albu")
        				.zipCode("-1122+")
        				.build();
        	
        		ProvidersRequest providersRequest = ProvidersRequest.builder()
        				.address(address)
        				.autoAssignment("Y")
        				.contractId("12344")
        				.memberId("01")
        				.pcpEffectiveDate("02-04-2022")
        				.sourceSystem("source_01")
        				.userId("1234")
        				.build();
        	validator.validateProvidersRequest(providersRequest);
        	}catch(ServiceException exception) {
        		Assertions.assertEquals(exception.getErrorCode().toString() ,
        				PCPSearchServiceErrors.PROVIDERS_ZIP_FORMAT.name());
        	}
	}
}
