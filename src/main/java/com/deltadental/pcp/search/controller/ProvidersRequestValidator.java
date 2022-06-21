package com.deltadental.pcp.search.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.annotation.ExcludeFromJacocoGeneratedReport;
import com.deltadental.pcp.search.domain.Address;
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
			validateForAutoAssignment(request.getAutoAssignment());
			validateForContractId(request.getContractId());
			validateForMemberId(request.getMemberId());
			validateForPCPEffectiveDate(request.getPcpEffectiveDate());
			validateForBlankField(request.getSourceSystem(), PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM);
			validateForBlankField(request.getUserId(), PCPSearchServiceErrors.PROVIDERS_USERID);
			validateForAdressField(request.getAddress());
		} else {
			throw PCPSearchServiceErrors.PROVIDER_SEARCH_REQUEST.createException();
		}

		log.info("END ProvidersRequestValidator.validateProvidersRequest()");
	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForAutoAssignment(String autoAssignment) {
		if (StringUtils.isBlank(autoAssignment)) {
			throw PCPSearchServiceErrors.PCP_SEARCH_001.createException();
		} else if (!CollectionUtils.containsAny(VALID_AUTO_SIGN_FLAGS, autoAssignment)) {
			throw PCPSearchServiceErrors.PCP_SEARCH_002.createException();
		}
	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForMemberId(String memberId) {
		if (StringUtils.isBlank(memberId)) {
			throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
		} else {
			if (StringUtils.trim(memberId).length() != 2) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException();
			}
			if (!allowDigitsOnly(memberId)) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID_DIGITS_ONLY.createException();
			}
		}

	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForContractId(String contractId) {
		if (StringUtils.isEmpty(contractId)) {
			throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException();
		} else if (!allowDigitsOnly(contractId)) {
			throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID_DIGITS_ONLY.createException();
		}
	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForPCPEffectiveDate(String pcpEffectiveDate) {
		if (StringUtils.isBlank(pcpEffectiveDate)) {
			throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
		} else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				LocalDate.parse(pcpEffectiveDate, formatter);
			} catch (Exception e) {
				throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException();
			}
		}

	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForBlankField(String field, PCPSearchServiceErrors pcpSearchServiceErrors) {
		if (StringUtils.isBlank(field)) {
			throw pcpSearchServiceErrors.createException();
		}
	}

	@ExcludeFromJacocoGeneratedReport
	private void validateForAdressField(Address address) {
		if (address == null) {
			throw PCPSearchServiceErrors.ADDRESS_REQUIRED.createException();
		} else {
			if (StringUtils.isBlank(address.getZipCode())) {
				throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException();
			} else {
				if (!isZipCodeValid(address.getZipCode())) {
					throw PCPSearchServiceErrors.PROVIDERS_ZIP_FORMAT.createException();
				}
			}
		}
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
