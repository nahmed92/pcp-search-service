package com.deltadental.pcp.search.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.BPBLResolutionResponse;
import com.deltadental.pcp.search.domain.BlResolutionResponse;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.FacilityResponse;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.RetrieveDistinctExceptionGroupsRes;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.pcp.search.domain.StatePcpAssignmentResponse;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceException;
import com.deltadental.platform.pcp.stub.FacilitySearchResponse;
import com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevels;
import com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetBussinessLevels;
import com.deltadental.platform.pcp.stub.GetBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignment;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignmentResponse;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevelResponse;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.ProvidersResponse;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroups;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroupsResponse;

import lombok.extern.slf4j.Slf4j;

@Service("pcpSearchService")
@Slf4j
public class PCPSearchServiceImpl implements PCPSearchService {

	@Autowired
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;
	
	@Autowired
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;
	
	@Autowired
	private PCPSearchResponseTransformer pcpSearchResponseTransformer;
	
	@Override
	@MethodExecutionTime
	public FacilityResponse facilitySearch(@Valid @NotNull FacilitySearchRequest facilitySearchRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.facilitySearch");
		try {
			com.deltadental.platform.pcp.stub.FacilitySearch facilitySearch = pcpSearchRequestTransformer.transformFacilitySearch(facilitySearchRequest);
			FacilitySearchResponse facilitySearchResponse =  pcpAssignmentSoapClient.facilitySearch(facilitySearch);
			FacilityResponse facilityResponse = pcpSearchResponseTransformer.transformFaclilitySearchResponse(facilitySearchResponse);
			log.info("END PCPSearchServiceImpl.facilitySearch");
			return facilityResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch facility search results for request" + facilitySearchRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(facilitySearchRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public BPBLResolutionResponse getBPsAndBussinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getBPsAndBussinessLevels");
		try {
			GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
			GetBPsAndBussinessLevels getBPsAndBussinessLevels = new GetBPsAndBussinessLevels();
			getBPsAndBussinessLevels.setArg0(getBussinessLevels.getArg0());
			GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = pcpAssignmentSoapClient.getBPsAndBussinessLevels(getBPsAndBussinessLevels);
			BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse(getBPsAndBussinessLevelsResponse);
			log.info("END PCPSearchServiceImpl.getBPsAndBussinessLevels");
			return bpblResolutionResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch BPs and Business Levels results for request" + blServiceRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(blServiceRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public BlResolutionResponse getBusinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getBusinessLevels");
		try {
			GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
			GetBussinessLevelsResponse getBussinessLevelsResponse = pcpAssignmentSoapClient.getBussinessLevels(getBussinessLevels);
			BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformStubGetBussinessLevelsResponse(getBussinessLevelsResponse);
			log.info("END PCPSearchServiceImpl.getBusinessLevels");
			return blResolutionResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch Business Levels results for request" + blServiceRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(blServiceRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getProviders");
		try {
			GetProviders getProviders = pcpSearchRequestTransformer.transformGetProviders(pcpAssignmentRequest);
			GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
			PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
			log.info("END PCPSearchServiceImpl.getProviders");
			return pcpAssignmentResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + pcpAssignmentRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public StatePcpAssignmentResponse getStatePCPAssignment(@Valid StatePcpAssignmentRequest statePcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getStatePCPAssignment");
		try {
			GetStatePCPAssignment stubGetStatePCPAssignment = pcpSearchRequestTransformer.transformGetStatePCPAssignment(statePcpAssignmentRequest);
			GetStatePCPAssignmentResponse getStatePCPAssignmentResponse = pcpAssignmentSoapClient.getStatePCPAssignment(stubGetStatePCPAssignment);
			StatePcpAssignmentResponse statePcpAssignmentResponse = pcpSearchResponseTransformer.transformGetStatePCPAssignmentResponse(getStatePCPAssignmentResponse);
			log.info("START PCPSearchServiceImpl.getStatePCPAssignment");
			return statePcpAssignmentResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch state pcp assignment for request" + statePcpAssignmentRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(statePcpAssignmentRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public BPBLResolutionResponse groupBenefitPackBussinessLevel(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.groupBenefitPackBussinessLevel");
		try {
			GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = pcpSearchRequestTransformer.transformGroupBenefitPackBussinessLevel(blServiceRequest);
			GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = pcpAssignmentSoapClient.groupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
			BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse(groupBenefitPackBussinessLevelResponse);
			log.info("END PCPSearchServiceImpl.groupBenefitPackBussinessLevel");
			return bpblResolutionResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to group benefit pack business level for request" + blServiceRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(blServiceRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse providerValidate(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.providerValidate");
		try {
			ProviderValidate stubProviderValidate = pcpSearchRequestTransformer.transformProviderValidate(pcpAssignmentRequest);
			ProviderValidateResponse providerValidateResponse = pcpAssignmentSoapClient.providerValidate(stubProviderValidate);
			PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformStubProviderValidateResponse(providerValidateResponse);
			log.info("END PCPSearchServiceImpl.providerValidate");
			return pcpAssignmentResponse;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to retrive provider validate for request" + pcpAssignmentRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		return null;
	}

	@Override
	@MethodExecutionTime
	public RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) throws ServiceException {
		log.info("START PCPSearchServiceImpl.retrieveDistinctExceptionGroups");
		try {
			RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups = new RetrieveDistinctExceptionGroups();
			retrieveDistinctExceptionGroups.setArg0(retrieveDistinct);
			RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = pcpAssignmentSoapClient.retrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
			RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = RetrieveDistinctExceptionGroupsRes.builder().retrieveDistinctExceptionGroupsResponse(retrieveDistinctExceptionGroupsResponse.getReturn()).build();
			log.info("END PCPSearchServiceImpl.retrieveDistinctExceptionGroups");
			return retrieveDistinctExceptionGroupsRes;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to retrive distinct exception groups for request" + retrieveDistinct, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(retrieveDistinct);
		}
		return null;
	}

	@Override
	public com.deltadental.pcp.search.domain.ProvidersResponse providers(ProvidersRequest providersRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.providers");
		try {
			Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
			ProvidersResponse providersResponse = pcpAssignmentSoapClient.providers(providers);
			com.deltadental.pcp.search.domain.ProvidersResponse response = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
			log.info("END PCPSearchServiceImpl.providers");
			return response;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + providersRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(providersRequest);
		}
		return null;
	}

	@Override
	public PCPAssignmentResponse pcpValidate(PCPValidateRequest pcpValidateRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.pcpValidate");
		try {
			PcpValidate providers = pcpSearchRequestTransformer.transformPcpValidateRequest(pcpValidateRequest);
			PcpValidateResponse pcpValidateResponse = pcpAssignmentSoapClient.pcpValidate(providers);
			PCPAssignmentResponse response = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);
			log.info("END PCPSearchServiceImpl.pcpValidate");
			return response;
		} catch(ServiceException se) {
			if (!PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.name().equals(se.getErrorCode())) {
			    throw se;
			}
		} catch (Exception exception) {
			log.error("Unable to perform pcp validation for request" + pcpValidateRequest, exception);
		    throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException(pcpValidateRequest);
		}
		return null;
	}

}
