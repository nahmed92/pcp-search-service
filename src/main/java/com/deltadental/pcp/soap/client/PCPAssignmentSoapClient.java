package com.deltadental.pcp.soap.client;

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

public interface PCPAssignmentSoapClient {

	FacilitySearchResponse facilitySearch(FacilitySearchRequest facilitySearchRequest);
	GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevels(GetBPsAndBussinessLevels getBPsAndBussinessLevels);
	GetBussinessLevelsResponse getBussinessLevels(GetBussinessLevels getBussinessLevels);
	GetProvidersResponse getProviders(GetProviders getProviders);
	GetStatePCPAssignmentResponse getStatePCPAssignment(GetStatePCPAssignment getStatePCPAssignment);
	GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevel(GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel);
	ProviderValidateResponse providerValidate(ProviderValidate providerValidate);
	RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroups(RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups);
}