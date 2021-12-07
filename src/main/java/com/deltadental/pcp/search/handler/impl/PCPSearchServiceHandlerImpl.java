package com.deltadental.pcp.search.handler.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.BPBLResolutionResponse;
import com.deltadental.pcp.search.domain.BlResolutionResponse;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.FacilityResponse;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.RetrieveDistinctExceptionGroupsRes;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.pcp.search.domain.StatePcpAssignmentResponse;
import com.deltadental.pcp.search.handler.PCPSearchServiceHandler;
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
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroups;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroupsResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PCPSearchServiceHandlerImpl implements PCPSearchServiceHandler {

	@Autowired
	PCPAssignmentSoapClient pcpAssignmentSoapClient;
	
	@Autowired
	PCPSearchRequestTransformer pcpSearchRequestTransformer;
	
	@Autowired
	PCPSearchResponseTransformer pcpSearchResponseTransformer;
	
	@Override
	@MethodExecutionTime
	public FacilityResponse facilitySearch(@Valid FacilitySearchRequest facilitySearchRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.facilitySearch");
		com.deltadental.platform.pcp.stub.FacilitySearch facilitySearch = pcpSearchRequestTransformer.transformFacilitySearchRequest(facilitySearchRequest);
		FacilitySearchResponse facilitySearchResponse =  pcpAssignmentSoapClient.facilitySearch(facilitySearch);
		FacilityResponse facilityResponse = pcpSearchResponseTransformer.transformFaclilitySearchResponse(facilitySearchResponse);
		log.info("END PCPSearchServiceHandlerImpl.facilitySearch");
		return facilityResponse;
	}

	@Override
	@MethodExecutionTime
	public BlResolutionResponse getBPsAndBussinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.getBPsAndBussinessLevels");
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformBlServiceRequest(blServiceRequest);
		GetBPsAndBussinessLevels getBPsAndBussinessLevels = new GetBPsAndBussinessLevels();
		getBPsAndBussinessLevels.setArg0(getBussinessLevels.getArg0());
		GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = pcpAssignmentSoapClient.getBPsAndBussinessLevels(getBPsAndBussinessLevels);
		BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse(getBPsAndBussinessLevelsResponse);
		log.info("END PCPSearchServiceHandlerImpl.getBPsAndBussinessLevels");
		return blResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public BlResolutionResponse getBusinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.getBusinessLevels");
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformBlServiceRequest(blServiceRequest);
		GetBussinessLevelsResponse getBussinessLevelsResponse = pcpAssignmentSoapClient.getBussinessLevels(getBussinessLevels);
		BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformStubGetBussinessLevelsResponse(getBussinessLevelsResponse);
		log.info("END PCPSearchServiceHandlerImpl.getBusinessLevels");
		return blResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.getProviders");
		GetProviders getProviders = pcpSearchRequestTransformer.transformPcpAssignmentRequest(pcpAssignmentRequest);
		GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
		log.info("END PCPSearchServiceHandlerImpl.getProviders");
		return pcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public StatePcpAssignmentResponse getStatePCPAssignment(@Valid StatePcpAssignmentRequest statePcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.getStatePCPAssignment");
		GetStatePCPAssignment stubGetStatePCPAssignment = pcpSearchRequestTransformer.transformStatePCPAssignmentRequest(statePcpAssignmentRequest);
		GetStatePCPAssignmentResponse getStatePCPAssignmentResponse = pcpAssignmentSoapClient.getStatePCPAssignment(stubGetStatePCPAssignment);
		StatePcpAssignmentResponse statePcpAssignmentResponse = pcpSearchResponseTransformer.transformGetStatePCPAssignmentResponse(getStatePCPAssignmentResponse);
		log.info("START PCPSearchServiceHandlerImpl.getStatePCPAssignment");
		return statePcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public BPBLResolutionResponse groupBenefitPackBussinessLevel(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.groupBenefitPackBussinessLevel");
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = pcpSearchRequestTransformer.transferGroupBenefitPackBussinessLevel(blServiceRequest);
		GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = pcpAssignmentSoapClient.groupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse(groupBenefitPackBussinessLevelResponse);
		log.info("END PCPSearchServiceHandlerImpl.groupBenefitPackBussinessLevel");
		return bpblResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse providerValidate(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.providerValidate");
		ProviderValidate stubProviderValidate = pcpSearchRequestTransformer.transformProviderValidate(pcpAssignmentRequest);
		ProviderValidateResponse providerValidateResponse = pcpAssignmentSoapClient.providerValidate(stubProviderValidate);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformStubProviderValidateResponse(providerValidateResponse);
		log.info("END PCPSearchServiceHandlerImpl.providerValidate");
		return pcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) throws ServiceException {
		log.info("START PCPSearchServiceHandlerImpl.retrieveDistinctExceptionGroups");
		RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups = new RetrieveDistinctExceptionGroups();
		retrieveDistinctExceptionGroups.setArg0(retrieveDistinct);
		RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = pcpAssignmentSoapClient.retrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = new RetrieveDistinctExceptionGroupsRes();
		retrieveDistinctExceptionGroupsRes.setRetrieveDistinctExceptionGroupsResponse(retrieveDistinctExceptionGroupsResponse.getReturn());
		log.info("END PCPSearchServiceHandlerImpl.retrieveDistinctExceptionGroups");
		return retrieveDistinctExceptionGroupsRes;
	}

}
