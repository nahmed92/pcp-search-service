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
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlResolutionResponse getBPsAndBussinessLevels(BlServiceRequest blServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlResolutionResponse getBusinessLevels(BlServiceRequest blServiceRequest) {
		
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BPBLResolutionResponse groupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PCPAssignmentResponse providerValidate(PcpAssignmentRequest pcpAssignmentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) {
		// TODO Auto-generated method stub
		return null;
	}

}
