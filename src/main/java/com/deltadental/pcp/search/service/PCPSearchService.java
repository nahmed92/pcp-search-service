package com.deltadental.pcp.search.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.EnrolleeDetail;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceException;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.ProvidersResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service("pcpSearchService")
@Slf4j
@Data
public class PCPSearchService {

	private static final String FAIL = "Fail";
	
	@Autowired
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;

	@Autowired
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;

	@Autowired
	private PCPSearchResponseTransformer pcpSearchResponseTransformer;

	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest)
			throws ServiceException {
		log.info("START PCPSearchService.getProviders");
		PCPAssignmentResponse pcpAssignmentResponse = null;
		try {
			GetProviders getProviders = pcpSearchRequestTransformer.transformGetProviders(pcpAssignmentRequest);
			GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
			pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + pcpAssignmentRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		log.info("END PCPSearchService.getProviders");
		return pcpAssignmentResponse;
	}	

	@MethodExecutionTime
	public com.deltadental.pcp.search.domain.ProvidersResponse providers(ProvidersRequest providersRequest)
			throws ServiceException {
		log.info("START PCPSearchService.providers");
		com.deltadental.pcp.search.domain.ProvidersResponse response = null;
		try {
			Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
			ProvidersResponse providersResponse = pcpAssignmentSoapClient.providers(providers);
			response = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
			setProcessStatusCode(response);
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + providersRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(providersRequest);
		}
		log.info("END PCPSearchService.providers");
		return response;
	}

	@MethodExecutionTime
	public PCPAssignmentResponse validate(PCPValidateRequest pcpValidateRequest) throws ServiceException {
		log.info("START PCPSearchService.validate");
		PCPAssignmentResponse response = null;
		try {
			PcpValidate providers = pcpSearchRequestTransformer.transformPcpValidateRequest(pcpValidateRequest);
			PcpValidateResponse pcpValidateResponse = pcpAssignmentSoapClient.pcpValidate(providers);
			response = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);
		} catch (Exception exception) {
			log.error("Unable to perform pcp validation for request {} ", pcpValidateRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpValidateRequest);
		}
		log.info("END PCPSearchService.validate");
		return response;
	}

	private void setProcessStatusCode(com.deltadental.pcp.search.domain.ProvidersResponse response) {
		log.info("START PCPAssignmentService.setProcessStatusCode()");
		if(response != null) {
			PCPAssignmentResponse pcpAssignmentResponse = response.getPcpAssignmentResponse();
			if(null != pcpAssignmentResponse) {
				List<PCPResponse> pcpResponses = pcpAssignmentResponse.getPcpResponses();
				if(CollectionUtils.isNotEmpty(pcpResponses)) {
					for (PCPResponse pcpResponse : pcpResponses) {
						List<EnrolleeDetail> enrollees = pcpResponse.getEnrollees();
						for (EnrolleeDetail enrolleeDetail : enrollees) {
							List<String> errorMessages = enrolleeDetail.getErrorMessages();
							if(CollectionUtils.isNotEmpty(errorMessages)) {
								pcpAssignmentResponse.setProcessStatusCode(FAIL);
							}
						}
					}
				}
			}
		}
		log.info("END PCPAssignmentService.setProcessStatusCode()");
	}
}
