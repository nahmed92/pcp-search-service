package com.deltadental.pcp.search.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClientImpl;

@Configuration
public class PcpAssignmentWSConfig {

	private static final String WS_STUB_PACKAGE = "com.deltadental.platform.pcp.stub";

	@Autowired
	ApplicationConfig authenticationConfig;

	public PcpAssignmentWSConfig() {

	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(WS_STUB_PACKAGE);
		return jaxb2Marshaller;
	}

	@Bean(name = "pcpAssignmentSoapClient")
    public PCPAssignmentSoapClient pcpAssignmentSoapClient(Jaxb2Marshaller marshaller) {
		PCPAssignmentSoapClientImpl pcpAssignmentSoapClient = new PCPAssignmentSoapClientImpl();
		pcpAssignmentSoapClient.setDefaultUri(authenticationConfig.getPcpWSSoapUri());
		pcpAssignmentSoapClient.setMarshaller(marshaller);
		pcpAssignmentSoapClient.setUnmarshaller(marshaller);
        return pcpAssignmentSoapClient;
    }

}
