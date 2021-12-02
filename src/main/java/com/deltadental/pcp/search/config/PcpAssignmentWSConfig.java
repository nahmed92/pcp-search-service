package com.deltadental.pcp.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClientImpl;

@Configuration
public class PcpAssignmentWSConfig {

	private static final String WS_STUB_PACKAGE = "com.deltadental.platform.pcp.stub";
	private String pcpWSSoapUri = "http://aw-lx0558.deltadev.ent:24011/pcpservices/assignment?wsdl";

	public PcpAssignmentWSConfig() {

	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath(WS_STUB_PACKAGE);
		return marshaller;
	}

	@Bean(name = "pcpAssignmentSoapClient")
    public PCPAssignmentSoapClient pcpAssignmentSoapClient(Jaxb2Marshaller marshaller) {
		PCPAssignmentSoapClientImpl pcpAssignmentSoapClient = new PCPAssignmentSoapClientImpl();
		pcpAssignmentSoapClient.setDefaultUri(pcpWSSoapUri);
		pcpAssignmentSoapClient.setMarshaller(marshaller);
		pcpAssignmentSoapClient.setUnmarshaller(marshaller);
        return pcpAssignmentSoapClient;
    }

}
