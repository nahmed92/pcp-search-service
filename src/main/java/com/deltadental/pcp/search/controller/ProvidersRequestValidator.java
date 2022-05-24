package com.deltadental.pcp.search.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProvidersRequestValidator {

	private static final List<String> VALID_AUTO_SIGN_FLAGS = List.of("Y", "N");

	public void validateProvidersRequest(ProvidersRequest request) throws ServiceException {

		log.info("START ProvidersRequestValidator.validateProvidersRequest()");
		if (request != null) {

			if (StringUtils.isBlank(request.getAutoAssignment())) {
				throw PCPSearchServiceErrors.PCP_SEARCH_001.createException();
			}
			if (!CollectionUtils.containsAny(VALID_AUTO_SIGN_FLAGS, request.getAutoAssignment())) {
				throw PCPSearchServiceErrors.PCP_SEARCH_002.createException();
			}

			if (StringUtils.isEmpty(request.getContractId())) {
				throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException();
			} else {
				if(!allowDigitsOnly(request.getContractId())) {
					throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.createException();
				}
			}

			if (StringUtils.isBlank(request.getMemberId())) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
			} else {
				if (StringUtils.trim(request.getMemberId()).length() != 2) {
					throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
				}
				if(!allowDigitsOnly(request.getMemberId())) {
					throw PCPSearchServiceErrors.PROVIDERS_MEMBERID_DIGITS_ONLY.createException();
				}
			}

			if (StringUtils.isBlank(request.getPcpEffectiveDate())) {
				throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
			} else {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
					LocalDate.parse(request.getPcpEffectiveDate(), formatter);
				} catch (Exception e) {
					throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
				}
			}

			if (StringUtils.isBlank(request.getSourceSystem())) {
				throw PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.createException();
			}

			if (StringUtils.isBlank(request.getUserId())) {
				throw PCPSearchServiceErrors.PROVIDERS_USERID.createException();
			}

			if(request.getAddress() != null) {
				if (StringUtils.isBlank(request.getAddress().getZipCode())) {
					throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException();
				} else {
					if (!isZipCodeValid(request.getAddress().getZipCode())) {
						throw PCPSearchServiceErrors.PROVIDERS_ZIP_FORMAT.createException();
					}
				}
			} else {
				throw PCPSearchServiceErrors.ADDRESS_REQUIRED.createException();
			}
			
		} else {
			throw PCPSearchServiceErrors.PROVIDER_SEARCH_REQUEST.createException();
		}

		log.info("END ProvidersRequestValidator.validateProvidersRequest()");
	}
	
	public boolean isZipCodeValid(String zipCode) {
		String regex = "^[0-9]{5}(?:-[0-9]{4})?$";		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zipCode);
		return matcher.matches();
	}
	
	public boolean allowDigitsOnly(String field) {
		String regex = "\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field);
		return matcher.matches();
	}
}
