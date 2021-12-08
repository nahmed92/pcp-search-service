package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class StatePcpAssignmentRequest {

	private String sourceSystem;
	private String groupNumber;
	private String divisionNumber;
	private String enrolleeNumber;
	private String dependentStateCode;
}
