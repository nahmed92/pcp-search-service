package com.deltadental.pcp.search.transformer;

import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.platform.pcp.stub.GetProviders;

@Component
public class PCPSearchRequestTransformer {

	public GetProviders transformPcpAssignmentRequest(PcpAssignmentRequest pcpAssignmentRequest) {
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = new com.deltadental.platform.pcp.stub.PcpAssignmentRequest();
		GetProviders getProviders = new GetProviders();
		getProviders.setArg0(assignmentRequest);
		return getProviders;
		
	}
}
