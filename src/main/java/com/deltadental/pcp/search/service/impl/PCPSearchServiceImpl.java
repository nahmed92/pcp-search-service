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
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.RetrieveDistinctExceptionGroupsRes;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.pcp.search.domain.StatePcpAssignmentResponse;
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
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroups;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroupsResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
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
		com.deltadental.platform.pcp.stub.FacilitySearch facilitySearch = pcpSearchRequestTransformer.transformFacilitySearch(facilitySearchRequest);
		FacilitySearchResponse facilitySearchResponse =  pcpAssignmentSoapClient.facilitySearch(facilitySearch);
		FacilityResponse facilityResponse = pcpSearchResponseTransformer.transformFaclilitySearchResponse(facilitySearchResponse);
		log.info("END PCPSearchServiceImpl.facilitySearch");
		return facilityResponse;
	}

	@Override
	@MethodExecutionTime
	public BPBLResolutionResponse getBPsAndBussinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getBPsAndBussinessLevels");
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
		GetBPsAndBussinessLevels getBPsAndBussinessLevels = new GetBPsAndBussinessLevels();
		getBPsAndBussinessLevels.setArg0(getBussinessLevels.getArg0());
		GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = pcpAssignmentSoapClient.getBPsAndBussinessLevels(getBPsAndBussinessLevels);
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse(getBPsAndBussinessLevelsResponse);
		log.info("END PCPSearchServiceImpl.getBPsAndBussinessLevels");
		return bpblResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public BlResolutionResponse getBusinessLevels(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getBusinessLevels");
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformGetBussinessLevels(blServiceRequest);
		GetBussinessLevelsResponse getBussinessLevelsResponse = pcpAssignmentSoapClient.getBussinessLevels(getBussinessLevels);
		BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformStubGetBussinessLevelsResponse(getBussinessLevelsResponse);
		log.info("END PCPSearchServiceImpl.getBusinessLevels");
		return blResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getProviders");
		GetProviders getProviders = pcpSearchRequestTransformer.transformGetProviders(pcpAssignmentRequest);
		GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
		log.info("END PCPSearchServiceImpl.getProviders");
		return pcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public StatePcpAssignmentResponse getStatePCPAssignment(@Valid StatePcpAssignmentRequest statePcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.getStatePCPAssignment");
		GetStatePCPAssignment stubGetStatePCPAssignment = pcpSearchRequestTransformer.transformGetStatePCPAssignment(statePcpAssignmentRequest);
		GetStatePCPAssignmentResponse getStatePCPAssignmentResponse = pcpAssignmentSoapClient.getStatePCPAssignment(stubGetStatePCPAssignment);
		StatePcpAssignmentResponse statePcpAssignmentResponse = pcpSearchResponseTransformer.transformGetStatePCPAssignmentResponse(getStatePCPAssignmentResponse);
		log.info("START PCPSearchServiceImpl.getStatePCPAssignment");
		return statePcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public BPBLResolutionResponse groupBenefitPackBussinessLevel(@Valid BlServiceRequest blServiceRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.groupBenefitPackBussinessLevel");
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = pcpSearchRequestTransformer.transformGroupBenefitPackBussinessLevel(blServiceRequest);
		GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = pcpAssignmentSoapClient.groupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse(groupBenefitPackBussinessLevelResponse);
		log.info("END PCPSearchServiceImpl.groupBenefitPackBussinessLevel");
		return bpblResolutionResponse;
	}

	@Override
	@MethodExecutionTime
	public PCPAssignmentResponse providerValidate(@Valid PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException {
		log.info("START PCPSearchServiceImpl.providerValidate");
		ProviderValidate stubProviderValidate = pcpSearchRequestTransformer.transformProviderValidate(pcpAssignmentRequest);
		ProviderValidateResponse providerValidateResponse = pcpAssignmentSoapClient.providerValidate(stubProviderValidate);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformStubProviderValidateResponse(providerValidateResponse);
		log.info("END PCPSearchServiceImpl.providerValidate");
		return pcpAssignmentResponse;
	}

	@Override
	@MethodExecutionTime
	public RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) throws ServiceException {
		log.info("START PCPSearchServiceImpl.retrieveDistinctExceptionGroups");
		RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups = new RetrieveDistinctExceptionGroups();
		retrieveDistinctExceptionGroups.setArg0(retrieveDistinct);
		RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = pcpAssignmentSoapClient.retrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = RetrieveDistinctExceptionGroupsRes.builder().retrieveDistinctExceptionGroupsResponse(retrieveDistinctExceptionGroupsResponse.getReturn()).build();
		log.info("END PCPSearchServiceImpl.retrieveDistinctExceptionGroups");
		return retrieveDistinctExceptionGroupsRes;
	}

}
