package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersRequest {
	
	private String contractId;
	
	private String memberId;
	
	private String pcpEffectiveDate;
	
	private String sourceSystem;
	
	private String autoAssignment;

	private String userId;
	
	private Address address;
}
