package com.deltadental.pcp.search.service;

import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;

public interface ProvidersAuditService {
	void saveProvidersAudit(ProvidersRequest providersRequest, ProvidersResponse providersResponse);
}
