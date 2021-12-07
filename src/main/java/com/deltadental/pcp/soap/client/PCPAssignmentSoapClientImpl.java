package com.deltadental.pcp.soap.client;

import javax.xml.bind.JAXBElement;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.deltadental.platform.pcp.stub.FacilitySearch;
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
import com.deltadental.platform.pcp.stub.ObjectFactory;
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroups;
import com.deltadental.platform.pcp.stub.RetrieveDistinctExceptionGroupsResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PCPAssignmentSoapClientImpl extends WebServiceGatewaySupport implements PCPAssignmentSoapClient {
    
	@Override
	public FacilitySearchResponse facilitySearch(FacilitySearch facilitySearch) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<FacilitySearch> jaxbElementFacilitySearch = objectFactory.createFacilitySearch(facilitySearch);
		JAXBElement<FacilitySearchResponse> facilitySearchResponse = (JAXBElement<FacilitySearchResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementFacilitySearch);
		return facilitySearchResponse.getValue();
	}

	@Override
	public GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevels(GetBPsAndBussinessLevels getBPsAndBussinessLevels) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<GetBPsAndBussinessLevels> jaxbGetBPsAndBussinessLevels = objectFactory.createGetBPsAndBussinessLevels(getBPsAndBussinessLevels);
		JAXBElement<GetBPsAndBussinessLevelsResponse> getBPsAndBussinessLevelsResponse = (JAXBElement<GetBPsAndBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbGetBPsAndBussinessLevels);
		return getBPsAndBussinessLevelsResponse.getValue();
	}

	@Override
	public GetBussinessLevelsResponse getBussinessLevels(GetBussinessLevels getBussinessLevels) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<GetBussinessLevels> jaxbElementGetBussinessLevels = objectFactory.createGetBussinessLevels(getBussinessLevels);
		JAXBElement<GetBussinessLevelsResponse> getBussinessLevelsResponse = (JAXBElement<GetBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetBussinessLevels);
		return getBussinessLevelsResponse.getValue();
	}

	@Override
	public GetProvidersResponse getProviders(GetProviders getProviders) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<GetProviders> jaxbElementGetProviders = objectFactory.createGetProviders(getProviders);
		JAXBElement<GetProvidersResponse> getProvidersResponse = (JAXBElement<GetProvidersResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetProviders);
		return getProvidersResponse.getValue();
	}

	@Override
	public GetStatePCPAssignmentResponse getStatePCPAssignment(GetStatePCPAssignment getStatePCPAssignment) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<GetStatePCPAssignment> jaxbElementGetStatePCPAssignment = objectFactory.createGetStatePCPAssignment(getStatePCPAssignment);
		JAXBElement<GetStatePCPAssignmentResponse> getStatePCPAssignmentResponse = (JAXBElement<GetStatePCPAssignmentResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetStatePCPAssignment);
		return getStatePCPAssignmentResponse.getValue();
	}

	@Override
	public GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevel(GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<GroupBenefitPackBussinessLevel> jaxbElementGroupBenefitPackBussinessLevel = objectFactory.createGroupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		JAXBElement<GroupBenefitPackBussinessLevelResponse> groupBenefitPackBussinessLevelResponse = (JAXBElement<GroupBenefitPackBussinessLevelResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGroupBenefitPackBussinessLevel);
		return groupBenefitPackBussinessLevelResponse.getValue();
	}

	@Override
	public ProviderValidateResponse providerValidate(ProviderValidate providerValidate) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<ProviderValidate> jaxbElementProviderValidate = objectFactory.createProviderValidate(providerValidate);
		JAXBElement<ProviderValidateResponse> facilitySearchResponse = (JAXBElement<ProviderValidateResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementProviderValidate);
		return facilitySearchResponse.getValue();
	}

	@Override
	public RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroups(RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups) {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<RetrieveDistinctExceptionGroups> jaxbElement = objectFactory.createRetrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		JAXBElement<RetrieveDistinctExceptionGroupsResponse> retrieveDistinctExceptionGroupsResponse = (JAXBElement<RetrieveDistinctExceptionGroupsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
		return retrieveDistinctExceptionGroupsResponse.getValue();
	}

}
