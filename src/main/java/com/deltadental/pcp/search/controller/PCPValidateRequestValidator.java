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

			if (StringUtils.isEmpty(request.getContractId())) {
				throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException();
			} else {
				if(!allowDigitsOnly(request.getContractId())) {
					throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.createException();
				}
			}

			if (StringUtils.isBlank(request.getMemberType())) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
			} else {
				if (StringUtils.trim(request.getMemberType()).length() != 2) {
					throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
				}
				if(!allowDigitsOnly(request.getMemberType())) {
					throw PCPSearchServiceErrors.PROVIDERS_MEMBERID_DIGITS_ONLY.createException();
				}
			}

//			if (StringUtils.isBlank(request.getPcpEffDate())) {
//				throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
//			} else {
//				try {
//					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//					LocalDate.parse(request.getPcpEffectiveDate(), formatter);
//				} catch (Exception e) {
//					throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
//				}
//			}

			if (StringUtils.isBlank(request.getSourceSystem())) {
				throw PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.createException();
			}

//			if (StringUtils.isBlank(request.getUserId())) {
//				throw PCPSearchServiceErrors.PROVIDERS_USERID.createException();
//			}

		}  else {
			throw PCPSearchServiceErrors.PCP_VALIDATE_REQUEST.createException();
		}

		log.info("END PCPValidateRequestValidator.validate()");
	}
	
	public boolean allowDigitsOnly(String field) {
		String regex = "\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field);
		return matcher.matches();
	}
}
