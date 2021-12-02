package com.deltadental.pcp.soap.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.deltadental.platform.pcp.stub.FacilitySearchRequest;
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
public class PCPAssignmentSoapClientImpl extends WebServiceGatewaySupport implements PCPAssignmentSoapClient {

	@Override
	public FacilitySearchResponse facilitySearch(FacilitySearchRequest facilitySearchRequest) {
		FacilitySearchResponse facilitySearchResponse = (FacilitySearchResponse) getWebServiceTemplate().marshalSendAndReceive(facilitySearchRequest);
		return facilitySearchResponse;
	}

	@Override
	public GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevels(GetBPsAndBussinessLevels getBPsAndBussinessLevels) {
		GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse = (GetBPsAndBussinessLevelsResponse) getWebServiceTemplate().marshalSendAndReceive(getBPsAndBussinessLevels);
		return getBPsAndBussinessLevelsResponse;
	}

	@Override
	public GetBussinessLevelsResponse getBussinessLevels(GetBussinessLevels getBussinessLevels) {
		GetBussinessLevelsResponse getBussinessLevelsResponse = (GetBussinessLevelsResponse) getWebServiceTemplate().marshalSendAndReceive(getBussinessLevels);
		return getBussinessLevelsResponse;
	}

	@Override
	public GetProvidersResponse getProviders(GetProviders getProviders) {
		GetProvidersResponse getProvidersResponse = (GetProvidersResponse) getWebServiceTemplate().marshalSendAndReceive(getProviders);
		return getProvidersResponse;
	}

	@Override
	public GetStatePCPAssignmentResponse getStatePCPAssignment(GetStatePCPAssignment getStatePCPAssignment) {
		GetStatePCPAssignmentResponse getStatePCPAssignmentResponse = (GetStatePCPAssignmentResponse) getWebServiceTemplate().marshalSendAndReceive(getStatePCPAssignment);
		return getStatePCPAssignmentResponse;
	}

	@Override
	public GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevel(GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel) {
		GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse = (GroupBenefitPackBussinessLevelResponse) getWebServiceTemplate().marshalSendAndReceive(groupBenefitPackBussinessLevel);
		return groupBenefitPackBussinessLevelResponse;
	}

	@Override
	public ProviderValidateResponse providerValidate(ProviderValidate providerValidate) {
		ProviderValidateResponse facilitySearchResponse = (ProviderValidateResponse) getWebServiceTemplate().marshalSendAndReceive(providerValidate);
		return facilitySearchResponse;
	}

	@Override
	public RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroups(RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups) {
		RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroupsResponse = (RetrieveDistinctExceptionGroupsResponse) getWebServiceTemplate().marshalSendAndReceive(retrieveDistinctExceptionGroups);
		return retrieveDistinctExceptionGroupsResponse;
	}

}
