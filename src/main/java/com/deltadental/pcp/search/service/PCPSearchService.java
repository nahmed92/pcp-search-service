package com.deltadental.pcp.search.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.pcp.search.service.impl.ProvidersAuditService;
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

	@Autowired
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;

	@Autowired
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;

	@Autowired
	private PCPSearchResponseTransformer pcpSearchResponseTransformer;

	@Autowired
	private ProvidersAuditService providersAuditService;
	
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
			throw PCPSearchServiceErrors.GETPROVIDERS_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		log.info("END PCPSearchService.getProviders");
		return pcpAssignmentResponse;
	}	

	@MethodExecutionTime
	public com.deltadental.pcp.search.domain.ProvidersResponse searchProviders(ProvidersRequest providersRequest)
			throws ServiceException {
		log.info("START PCPSearchService.providers");
		com.deltadental.pcp.search.domain.ProvidersResponse response = null;
		try {
			Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
			ProvidersResponse providersResponse = pcpAssignmentSoapClient.providers(providers);
			response = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + providersRequest, exception);
			throw PCPSearchServiceErrors.PROVIDERS_SEARCH_ERROR.createException(providersRequest);
		} finally {
			providersAuditService.save(providersRequest, response);
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
			throw PCPSearchServiceErrors.PROVIDER_VALIDATE_ERROR.createException(pcpValidateRequest);
		}
		log.info("END PCPSearchService.validate");
		return response;
	}

	
}
