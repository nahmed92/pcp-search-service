package com.deltadental.pcp.soap.client;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
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
    
	@Autowired
	private ObjectFactory objectFactory;
	
	@Override
	public FacilitySearchResponse facilitySearch(FacilitySearch facilitySearch) {
		log.info("START PCPAssignmentSoapClientImpl.facilitySearch");
		JAXBElement<FacilitySearch> jaxbElementFacilitySearch = objectFactory.createFacilitySearch(facilitySearch);
		JAXBElement<FacilitySearchResponse> facilitySearchResponse = (JAXBElement<FacilitySearchResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementFacilitySearch);
		log.info("END PCPAssignmentSoapClientImpl.facilitySearch");
		return facilitySearchResponse.getValue();
	}

	@Override
	public GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevels(GetBPsAndBussinessLevels getBPsAndBussinessLevels) {
		log.info("START PCPAssignmentSoapClientImpl.getBPsAndBussinessLevels");
		JAXBElement<GetBPsAndBussinessLevels> jaxbGetBPsAndBussinessLevels = objectFactory.createGetBPsAndBussinessLevels(getBPsAndBussinessLevels);
		JAXBElement<GetBPsAndBussinessLevelsResponse> getBPsAndBussinessLevelsResponse = (JAXBElement<GetBPsAndBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbGetBPsAndBussinessLevels);
		log.info("END PCPAssignmentSoapClientImpl.getBPsAndBussinessLevels");
		return getBPsAndBussinessLevelsResponse.getValue();
	}

	@Override
	public GetBussinessLevelsResponse getBussinessLevels(GetBussinessLevels getBussinessLevels) {
		log.info("START PCPAssignmentSoapClientImpl.getBussinessLevels");
		JAXBElement<GetBussinessLevels> jaxbElementGetBussinessLevels = objectFactory.createGetBussinessLevels(getBussinessLevels);
		JAXBElement<GetBussinessLevelsResponse> getBussinessLevelsResponse = (JAXBElement<GetBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetBussinessLevels);
		log.info("END PCPAssignmentSoapClientImpl.getBussinessLevels");
		return getBussinessLevelsResponse.getValue();
	}

	@Override
	public GetProvidersResponse getProviders(GetProviders getProviders) {
		log.info("START PCPAssignmentSoapClientImpl.getProviders");
		JAXBElement<GetProviders> jaxbElementGetProviders = objectFactory.createGetProviders(getProviders);
		JAXBElement<GetProvidersResponse> getProvidersResponse = (JAXBElement<GetProvidersResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetProviders);
		log.info("END PCPAssignmentSoapClientImpl.getProviders");
		return getProvidersResponse.getValue();
	}

	@Override
	public GetStatePCPAssignmentResponse getStatePCPAssignment(GetStatePCPAssignment getStatePCPAssignment) {
		log.info("START PCPAssignmentSoapClientImpl.getStatePCPAssignment");
		JAXBElement<GetStatePCPAssignment> jaxbElementGetStatePCPAssignment = objectFactory.createGetStatePCPAssignment(getStatePCPAssignment);
		JAXBElement<GetStatePCPAssignmentResponse> getStatePCPAssignmentResponse = (JAXBElement<GetStatePCPAssignmentResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetStatePCPAssignment);
		log.info("END PCPAssignmentSoapClientImpl.getStatePCPAssignment");
		return getStatePCPAssignmentResponse.getValue();
	}

	@Override
	public GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevel(GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel) {
		log.info("START PCPAssignmentSoapClientImpl.groupBenefitPackBussinessLevel");
		JAXBElement<GroupBenefitPackBussinessLevel> jaxbElementGroupBenefitPackBussinessLevel = objectFactory.createGroupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		JAXBElement<GroupBenefitPackBussinessLevelResponse> groupBenefitPackBussinessLevelResponse = (JAXBElement<GroupBenefitPackBussinessLevelResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGroupBenefitPackBussinessLevel);
		log.info("END PCPAssignmentSoapClientImpl.groupBenefitPackBussinessLevel");
		return groupBenefitPackBussinessLevelResponse.getValue();
	}

	@Override
	public ProviderValidateResponse providerValidate(ProviderValidate providerValidate) {
		log.info("START PCPAssignmentSoapClientImpl.providerValidate");
		JAXBElement<ProviderValidate> jaxbElementProviderValidate = objectFactory.createProviderValidate(providerValidate);
		JAXBElement<ProviderValidateResponse> facilitySearchResponse = (JAXBElement<ProviderValidateResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementProviderValidate);
		log.info("END PCPAssignmentSoapClientImpl.providerValidate");
		return facilitySearchResponse.getValue();
	}

	@Override
	public RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroups(RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups) {
		log.info("START PCPAssignmentSoapClientImpl.retrieveDistinctExceptionGroups");
		JAXBElement<RetrieveDistinctExceptionGroups> jaxbElement = objectFactory.createRetrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		JAXBElement<RetrieveDistinctExceptionGroupsResponse> retrieveDistinctExceptionGroupsResponse = (JAXBElement<RetrieveDistinctExceptionGroupsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
		log.info("END PCPAssignmentSoapClientImpl.retrieveDistinctExceptionGroups");
		return retrieveDistinctExceptionGroupsResponse.getValue();
	}

}
