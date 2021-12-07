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
import com.deltadental.platform.common.exception.ServiceException;

public interface PCPSearchServiceHandler {
	
	FacilityResponse facilitySearch(FacilitySearchRequest facilitySearchRequest) throws ServiceException;
	BlResolutionResponse getBPsAndBussinessLevels(BlServiceRequest blServiceRequest) throws ServiceException;
	BlResolutionResponse getBusinessLevels(BlServiceRequest blServiceRequest) throws ServiceException;
	PCPAssignmentResponse getProviders(PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException;
	StatePcpAssignmentResponse getStatePCPAssignment(StatePcpAssignmentRequest statePcpAssignmentRequest) throws ServiceException;
	BPBLResolutionResponse groupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest) throws ServiceException;
	PCPAssignmentResponse providerValidate(PcpAssignmentRequest pcpAssignmentRequest) throws ServiceException;
	RetrieveDistinctExceptionGroupsRes retrieveDistinctExceptionGroups(String retrieveDistinct) throws ServiceException;
	
}
