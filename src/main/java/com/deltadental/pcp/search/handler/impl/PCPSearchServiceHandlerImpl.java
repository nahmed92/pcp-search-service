package com.deltadental.pcp.search.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class PCPSearchServiceHandlerImpl implements PCPSearchServiceHandler {

	@Autowired
	PCPAssignmentSoapClient pcpAssignmentSoapClient;
	
	@Autowired
	PCPSearchRequestTransformer pcpSearchRequestTransformer;
	
	@Autowired
	PCPSearchResponseTransformer pcpSearchResponseTransformer;
	
	@Override
	public FacilityResponse facilitySearch(FacilitySearchRequest facilitySearchRequest) {
		com.deltadental.platform.pcp.stub.FacilitySearchRequest searchRequest = pcpSearchRequestTransformer.transformFacilitySearchRequest(facilitySearchRequest);
		FacilitySearchResponse facilitySearchResponse =  pcpAssignmentSoapClient.facilitySearch(searchRequest);
		FacilityResponse facilityResponse = pcpSearchResponseTransformer.transformFaclilitySearchResponse(facilitySearchResponse);
		return facilityResponse;
	}

	@Override
	public BlResolutionResponse getBPsAndBussinessLevels(BlServiceRequest blServiceRequest) {
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformBlServiceRequest(blServiceRequest);
		GetBPsAndBussinessLevels getBPsAndBussinessLevels = new GetBPsAndBussinessLevels();
		getBPsAndBussinessLevels.setArg0(getBussinessLevels.getArg0());
		GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = pcpAssignmentSoapClient.getBPsAndBussinessLevels(getBPsAndBussinessLevels);
		BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse(getBPsAndBussinessLevelsResponse);
		return blResolutionResponse;
	}

	@Override
	public BlResolutionResponse getBusinessLevels(BlServiceRequest blServiceRequest) {
		GetBussinessLevels getBussinessLevels = pcpSearchRequestTransformer.transformBlServiceRequest(blServiceRequest);
		GetBussinessLevelsResponse getBussinessLevelsResponse = pcpAssignmentSoapClient.getBussinessLevels(getBussinessLevels);
		BlResolutionResponse blResolutionResponse = pcpSearchResponseTransformer.transformStubGetBussinessLevelsResponse(getBussinessLevelsResponse);
		return blResolutionResponse;
	}

	@Override
	public PCPAssignmentResponse getProviders(PcpAssignmentRequest pcpAssignmentRequest) {
		GetProviders getProviders = pcpSearchRequestTransformer.transformPcpAssignmentRequest(pcpAssignmentRequest);
		GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
		return pcpAssignmentResponse;
	}

	@Override
	public StatePcpAssignmentResponse getStatePCPAssignment(StatePcpAssignmentRequest statePcpAssignmentRequest) {
		GetStatePCPAssignment stubGetStatePCPAssignment = pcpSearchRequestTransformer.transformStatePCPAssignmentRequest(statePcpAssignmentRequest);
		GetStatePCPAssignmentResponse getStatePCPAssignmentResponse = pcpAssignmentSoapClient.getStatePCPAssignment(stubGetStatePCPAssignment);
		StatePcpAssignmentResponse statePcpAssignmentResponse = pcpSearchResponseTransformer.transformGetStatePCPAssignmentResponse(getStatePCPAssignmentResponse);
		return statePcpAssignmentResponse;
	}

	@Override
	public BPBLResolutionResponse groupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest) {
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = pcpSearchRequestTransformer.transferGroupBenefitPackBussinessLevel(blServiceRequest);
		GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = pcpAssignmentSoapClient.groupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse(groupBenefitPackBussinessLevelResponse);
		return bpblResolutionResponse;
	}

	@Override
	public PCPAssignmentResponse providerValidate(PcpAssignmentRequest pcpAssignmentRequest) {
		ProviderValidate stubProviderValidate = pcpSearchRequestTransformer.transformProviderValidate(pcpAssignmentRequest);
		ProviderValidateResponse providerValidateResponse = pcpAssignmentSoapClient.providerValidate(stubProviderValidate);
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchResponseTransformer.transformStubProviderValidateResponse(providerValidateResponse);
		return pcpAssignmentResponse;
	}

	@Override
	public RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) {
		RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups = new RetrieveDistinctExceptionGroups();
		retrieveDistinctExceptionGroups.setArg0(retrieveDistinct);
		RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = pcpAssignmentSoapClient.retrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroupsRes = new RetrieveDistinctExceptionGroupsRes();
		retrieveDistinctExceptionGroupsRes.setRetrieveDistinctExceptionGroupsResponse(retrieveDistinctExceptionGroupsResponse.getReturn());
		return retrieveDistinctExceptionGroupsRes;
	}

}
