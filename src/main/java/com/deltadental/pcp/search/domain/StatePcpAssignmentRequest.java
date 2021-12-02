package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatePcpAssignmentRequest {

	private String sourceSystem;
	private String groupNumber;
	private String divisionNumber;
	private String enrolleeNumber;
	private String dependentStateCode;
}
