package com.deltadental.pcp.search.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
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

	@Autowired
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;

	@Autowired
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;

	@Autowired
	private PCPSearchResponseTransformer pcpSearchResponseTransformer;

	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest)
			throws ServiceException {
		log.info("START PCPSearchServiceImpl.getProviders");
		try {
			GetProviders getProviders = pcpSearchRequestTransformer.transformGetProviders(pcpAssignmentRequest);
			GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
			PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer
					.transformGetProvidersResponse(getProvidersResponse);
			log.info("END PCPSearchServiceImpl.getProviders");
			return pcpAssignmentResponse;
		} catch (ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
				throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + pcpAssignmentRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		return null;
	}	

	@MethodExecutionTime
	public com.deltadental.pcp.search.domain.ProvidersResponse providers(ProvidersRequest providersRequest)
			throws ServiceException {
		log.info("START PCPSearchServiceImpl.providers");
		try {
			Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
			ProvidersResponse providersResponse = pcpAssignmentSoapClient.providers(providers);
			com.deltadental.pcp.search.domain.ProvidersResponse response = pcpSearchResponseTransformer
					.transformProvidersResponse(providersResponse);
			log.info("END PCPSearchServiceImpl.providers");
			return response;
		} catch (ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
				throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + providersRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(providersRequest);
		}
		return null;
	}

	@MethodExecutionTime
	public PCPAssignmentResponse validate(PCPValidateRequest pcpValidateRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.validate");
		try {
			PcpValidate providers = pcpSearchRequestTransformer.transformPcpValidateRequest(pcpValidateRequest);
			PcpValidateResponse pcpValidateResponse = pcpAssignmentSoapClient.pcpValidate(providers);
			PCPAssignmentResponse response = pcpSearchResponseTransformer
					.transformPcpValidateResponse(pcpValidateResponse);
			log.info("END PCPSearchServiceImpl.validate");
			return response;
		} catch (Exception exception) {
			log.error("Unable to perform pcp validation for request {} ", pcpValidateRequest, exception);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpValidateRequest);
		}
	}

}
