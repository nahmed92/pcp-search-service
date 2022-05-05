package com.deltadental.pcp.search.controller;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.common.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProvidersRequestValidator {

	public void validateProvidersRequest(ProvidersRequest providersRequest) throws ServiceException {
		if (providersRequest == null || StringUtils.isBlank(providersRequest.getAutoAssignment())) {
			throw PCPSearchServiceErrors.PROVIDERS_AUTOASSIGN.createException("Invalid Auto Aissgnment Flag");
		} else {
			List<String> autoAssignmentList = Arrays.asList("Y", "N");
			if(!autoAssignmentList.contains(StringUtils.trimToNull(providersRequest.getAutoAssignment()))) {
				throw PCPSearchServiceErrors.PROVIDERS_AUTOASSIGN.createException("Invalid Auto Aissgnment Flag");
			}
		}
		
		if (providersRequest == null || StringUtils.isEmpty(providersRequest.getContractID())) {
			throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException("Invalid Contract ID");
		}
		
		if (providersRequest == null || StringUtils.isBlank(providersRequest.getMemberId())) {
			throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException("Invalid Member ID");
		} else {
			if(StringUtils.trim(providersRequest.getMemberId()).length() != 2) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException("Invalid Member ID");
			}
		}
		
		if (providersRequest == null || providersRequest.getPcpEffectiveDate() == null) {
			throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException("Invalid PCP Effective Date");
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			try {
				formatter.format(providersRequest.getPcpEffectiveDate());
			} catch (Exception e) {
				throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException("Invalid PCP Effective Date");
			}
		}
		
		if (providersRequest == null || StringUtils.isBlank(providersRequest.getSourceSystem())) {
			throw PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.createException("Source System Cannot be empty.");
		}
		
		if (providersRequest == null || StringUtils.isBlank(providersRequest.getUserId())) {
			throw PCPSearchServiceErrors.PROVIDERS_USERID.createException("User ID Cannot be empty.");
		}
		
		if (providersRequest == null || providersRequest.getAddress() == null || StringUtils.isBlank(providersRequest.getAddress().getZipCode())) {
			throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException("Zip Code Cannot be empty.");
		} else {
			if(providersRequest != null || providersRequest.getAddress() != null || StringUtils.trim(providersRequest.getAddress().getZipCode()).length() < 5 || StringUtils.trim(providersRequest.getAddress().getZipCode()).length() > 9) {
				throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException("Zip Code Cannot be empty.");
			}
		}
	}
}
