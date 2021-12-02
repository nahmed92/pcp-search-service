package com.deltadental.pcp.search.transformer;

import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;

@Component
public class PCPSearchResponseTransformer {

	public PCPAssignmentResponse transformGetProvidersResponse(GetProvidersResponse getProvidersResponse) {
		
		PCPAssignmentResponse pcpAssignmentResponse = new PCPAssignmentResponse();
		return pcpAssignmentResponse;
		
	}
}
