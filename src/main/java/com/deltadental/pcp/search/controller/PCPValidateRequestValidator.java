package com.deltadental.pcp.search.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPValidateRequestValidator {

	public void validate(PCPValidateRequest request) throws ServiceException {
		log.info("START PCPValidateRequestValidator.validate()");
		if (request != null) {
			validateForContractId(request.getContractId());
			validateForMemberType(request.getMemberType());

			if (StringUtils.isBlank(request.getSourceSystem())) {
				throw PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.createException();
			}

		}  else {
			throw PCPSearchServiceErrors.PCP_VALIDATE_REQUEST.createException();
		}

		log.info("END PCPValidateRequestValidator.validate()");
	}
	
	
	private void validateForContractId(String contractId) {
		if (StringUtils.isEmpty(contractId)) {
			throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException();
		} else {
			if(!allowDigitsOnly(contractId)) {
				throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.createException();
			}
		}
	}
	
	private void validateForMemberType(String memberType) {
		if (StringUtils.isBlank(memberType)) {
			throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
		} else {
			if (StringUtils.trim(memberType).length() != 2) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
			}
			if(!allowDigitsOnly(memberType)) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID_DIGITS_ONLY.createException();
			}
		}
	}
	
	public boolean allowDigitsOnly(String field) {
		String regex = "\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field);
		return matcher.matches();
	}
}
