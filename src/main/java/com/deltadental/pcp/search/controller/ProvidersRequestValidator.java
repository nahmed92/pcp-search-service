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

	public void validate(ProvidersRequest providersRequest) throws ServiceException {
		if(null != providersRequest) {
			if (StringUtils.isBlank(providersRequest.getAutoAssignment())) {
				throw PCPSearchServiceErrors.PROVIDERS_AUTOASSIGN.createException("Invalid Auto Aissgnment Flag");
			} else {
				List<String> autoAssignmentList = Arrays.asList("Y", "N");
				if(!autoAssignmentList.contains(StringUtils.trimToNull(providersRequest.getAutoAssignment()))) {
					throw PCPSearchServiceErrors.PROVIDERS_AUTOASSIGN.createException("Invalid Auto Aissgnment Flag");
				}
			}
			
			if (StringUtils.isEmpty(providersRequest.getContractId())) {
				throw PCPSearchServiceErrors.PROVIDERS_CONTRACTID.createException("Invalid Contract ID");
			}
			
			if (StringUtils.isBlank(providersRequest.getMemberId())) {
				throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException("Invalid Member ID");
			} else {
				if(StringUtils.trim(providersRequest.getMemberId()).length() != 2) {
					throw PCPSearchServiceErrors.PROVIDERS_MEMBERID.createException("Invalid Member ID");
				}
			}
			
			if (providersRequest.getPcpEffectiveDate() == null) {
				throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException("Invalid PCP Effective Date");
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				try {
					formatter.format(providersRequest.getPcpEffectiveDate());
				} catch (Exception e) {
					throw PCPSearchServiceErrors.PROVIDERS_PCPEFFECTIVEDATE.createException("Invalid PCP Effective Date");
				}
			}
			
			if (StringUtils.isBlank(providersRequest.getSourceSystem())) {
				throw PCPSearchServiceErrors.PROVIDERS_SOURCESYSTEM.createException("Source System Cannot be empty.");
			}
			
			if (StringUtils.isBlank(providersRequest.getUserId())) {
				throw PCPSearchServiceErrors.PROVIDERS_USERID.createException("User ID Cannot be empty.");
			}
			
			if (providersRequest.getAddress() == null || StringUtils.isBlank(providersRequest.getAddress().getZipCode())) {
				throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException("Zip Code Cannot be empty.");
			} else {
				if(providersRequest.getAddress() != null) {
					if(StringUtils.trim(providersRequest.getAddress().getZipCode()).length() < 5 || StringUtils.trim(providersRequest.getAddress().getZipCode()).length() > 9) {
						throw PCPSearchServiceErrors.PROVIDERS_ZIP.createException("Zip Code Cannot be empty.");
					}
				} else {
					
				}
			}
		} else {
			
		}
		
	}
}
