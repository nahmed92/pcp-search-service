package com.deltadental.pcp.search.handler;

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

public interface PCPSearchServiceHandler {
	
	FacilityResponse facilitySearch(FacilitySearchRequest facilitySearchRequest);
	BlResolutionResponse getBPsAndBussinessLevels(BlServiceRequest blServiceRequest);
	BlResolutionResponse getBusinessLevels(BlServiceRequest blServiceRequest);
	PCPAssignmentResponse getProviders(PcpAssignmentRequest pcpAssignmentRequest);
	StatePcpAssignmentResponse getStatePCPAssignment(StatePcpAssignmentRequest statePcpAssignmentRequest);
	BPBLResolutionResponse groupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest);
	PCPAssignmentResponse providerValidate(PcpAssignmentRequest pcpAssignmentRequest);
	RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct);
	
}
