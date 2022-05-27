package com.deltadental.pcp.soap.client;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.platform.pcp.stub.ObjectFactory;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.ProvidersResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Slf4j
public class PCPAssignmentSoapClient extends WebServiceGatewaySupport   {
    
	@Autowired
	private ObjectFactory objectFactory;
	
	public ProvidersResponse providers(Providers providers) {
		log.info("START PCPAssignmentSoapClientImpl.providers()");
		try {
			JAXBElement<Providers> jaxbElementProviders = objectFactory.createProviders(providers);
			JAXBElement<ProvidersResponse> providersResponse = (JAXBElement<ProvidersResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElementProviders);
			log.info("END "+SOAPConstants.SOAP_RES_RECV+" PCPAssignmentSoapClientImpl.providers "+SOAPConstants.END_EXTERNAL_SOAP_CALL);
			return providersResponse.getValue();
		} catch (Exception exception) {
			log.error("Unable to process providers request {} ", providers,exception);
			throw PCPSearchServiceErrors.PROVIDERS_ERROR.createException();
		}	
	}

	public PcpValidateResponse pcpValidate(PcpValidate pcpValidate) {
		log.info("START PCPAssignmentSoapClientImpl.pcpValidate()");
		try {
			JAXBElement<PcpValidate> jaxbElement = objectFactory.createPcpValidate(pcpValidate);
			JAXBElement<PcpValidateResponse> pcpValidateResponse = (JAXBElement<PcpValidateResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
			log.info("END PCPAssignmentSoapClientImpl.pcpValidate()");
			return pcpValidateResponse.getValue();
		} catch (Exception exception) {
			log.error("Unable to validate pcp for request {}", pcpValidate, exception);
			throw PCPSearchServiceErrors.PCPVALIDATE_ERROR.createException();
		}
	}
}
