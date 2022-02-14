package com.deltadental.pcp.soap.client;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
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
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.ProvidersResponse;
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
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.facilitySearch "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		try {
			JAXBElement<FacilitySearch> jaxbElementFacilitySearch = objectFactory.createFacilitySearch(facilitySearch);
			JAXBElement<FacilitySearchResponse> facilitySearchResponse = (JAXBElement<FacilitySearchResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementFacilitySearch);
			log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.facilitySearch "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
			return facilitySearchResponse.getValue();
		} catch (Exception exception) {
			log.error("Exception occurred processing facility search request", SOAPConstants.SOAP_CALL, exception, null);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException();
		}
	}

	@Override
	public GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevels(GetBPsAndBussinessLevels getBPsAndBussinessLevels) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.getBPsAndBussinessLevels "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		try {
			JAXBElement<GetBPsAndBussinessLevels> jaxbGetBPsAndBussinessLevels = objectFactory.createGetBPsAndBussinessLevels(getBPsAndBussinessLevels);
			JAXBElement<GetBPsAndBussinessLevelsResponse> getBPsAndBussinessLevelsResponse = (JAXBElement<GetBPsAndBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbGetBPsAndBussinessLevels);
			log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.getBPsAndBussinessLevels "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
			return getBPsAndBussinessLevelsResponse.getValue();
		} catch (Exception exception) {
			log.error("Exception occurred processing get BPs and BLs request", SOAPConstants.SOAP_CALL, exception, null);
			throw PCPSearchServiceErrors.INTERNAL_SERVER_ERROR.createException();
		}
	}

	@Override
	public GetBussinessLevelsResponse getBussinessLevels(GetBussinessLevels getBussinessLevels) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.getBussinessLevels "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<GetBussinessLevels> jaxbElementGetBussinessLevels = objectFactory.createGetBussinessLevels(getBussinessLevels);
		JAXBElement<GetBussinessLevelsResponse> getBussinessLevelsResponse = (JAXBElement<GetBussinessLevelsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetBussinessLevels);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.getBussinessLevels "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return getBussinessLevelsResponse.getValue();
	}

	@Override
	public GetProvidersResponse getProviders(GetProviders getProviders) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.getProviders "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<GetProviders> jaxbElementGetProviders = objectFactory.createGetProviders(getProviders);
		JAXBElement<GetProvidersResponse> getProvidersResponse = (JAXBElement<GetProvidersResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetProviders);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.getProviders "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return getProvidersResponse.getValue();
	}

	@Override
	public GetStatePCPAssignmentResponse getStatePCPAssignment(GetStatePCPAssignment getStatePCPAssignment) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.getStatePCPAssignment "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<GetStatePCPAssignment> jaxbElementGetStatePCPAssignment = objectFactory.createGetStatePCPAssignment(getStatePCPAssignment);
		JAXBElement<GetStatePCPAssignmentResponse> getStatePCPAssignmentResponse = (JAXBElement<GetStatePCPAssignmentResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGetStatePCPAssignment);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.getStatePCPAssignment "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return getStatePCPAssignmentResponse.getValue();
	}

	@Override
	public GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevel(GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.groupBenefitPackBussinessLevel "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<GroupBenefitPackBussinessLevel> jaxbElementGroupBenefitPackBussinessLevel = objectFactory.createGroupBenefitPackBussinessLevel(groupBenefitPackBussinessLevel);
		JAXBElement<GroupBenefitPackBussinessLevelResponse> groupBenefitPackBussinessLevelResponse = (JAXBElement<GroupBenefitPackBussinessLevelResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementGroupBenefitPackBussinessLevel);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.groupBenefitPackBussinessLevel "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return groupBenefitPackBussinessLevelResponse.getValue();
	}

	@Override
	public ProviderValidateResponse providerValidate(ProviderValidate providerValidate) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.providerValidate "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<ProviderValidate> jaxbElementProviderValidate = objectFactory.createProviderValidate(providerValidate);
		JAXBElement<ProviderValidateResponse> providerValidateResponse = (JAXBElement<ProviderValidateResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementProviderValidate);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.providerValidate "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return providerValidateResponse.getValue();
	}

	@Override
	public ProvidersResponse providers(Providers providers) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.providers "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<Providers> jaxbElementProviders = objectFactory.createProviders(providers);
		JAXBElement<ProvidersResponse> providersResponse = (JAXBElement<ProvidersResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementProviders);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.providers "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return providersResponse.getValue();
	}

	@Override
	public RetrieveDistinctExceptionGroupsResponse retrieveDistinctExceptionGroups(RetrieveDistinctExceptionGroups retrieveDistinctExceptionGroups) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.retrieveDistinctExceptionGroups "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<RetrieveDistinctExceptionGroups> jaxbElement = objectFactory.createRetrieveDistinctExceptionGroups(retrieveDistinctExceptionGroups);
		JAXBElement<RetrieveDistinctExceptionGroupsResponse> retrieveDistinctExceptionGroupsResponse = (JAXBElement<RetrieveDistinctExceptionGroupsResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.retrieveDistinctExceptionGroups "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return retrieveDistinctExceptionGroupsResponse.getValue();
	}

	@Override
	public PcpValidateResponse pcpValidate(PcpValidate pcpValidate) {
		log.info("START "+SOAPConstants.SOAP_REQ_SEND+" PCPAssignmentSoapClientImpl.pcpValidate "+SOAPConstants.START_EXTERNAL_SOAP_CALL);
		JAXBElement<PcpValidate> jaxbElement = objectFactory.createPcpValidate(pcpValidate);
		JAXBElement<PcpValidateResponse> pcpValidateResponse = (JAXBElement<PcpValidateResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
		log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.pcpValidate "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
		return pcpValidateResponse.getValue();
	}

}
